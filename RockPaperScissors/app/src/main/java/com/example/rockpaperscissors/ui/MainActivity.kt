package com.example.rockpaperscissors.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.model.Game
import com.example.shoppinglist.Database.GameRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

const val HISTORY_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var gameRepository: GameRepository
    private var losses: Int = 0
    private var draws: Int = 0
    private var wins: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Rock, Paper, Scissors Kotlin"
        loadStatistics()

        gameRepository = GameRepository(this)
        initViews()
    }

    private fun initViews(){
        ivRock.setOnClickListener { play(Game.Choice.ROCK) }
        ivPaper.setOnClickListener { play(Game.Choice.PAPER) }
        ivScissors.setOnClickListener { play(Game.Choice.SCISSORS) }
    }

    private fun loadStatistics(){
        mainScope.launch {
            this@MainActivity.losses = 0
            this@MainActivity.draws = 0
            this@MainActivity.wins = 0
            withContext(Dispatchers.IO) {
                this@MainActivity.losses = gameRepository.getAmountOfLosses()
                this@MainActivity.draws = gameRepository.getAmountOfDraws()
                this@MainActivity.wins = gameRepository.getAmountOfWins()
            }
            tvStatistics.text = getString(R.string.statistics, wins.toString(), draws.toString(), losses.toString())
        }

    }

    private fun play(userChoice: Game.Choice){
        val botChoice = Game.Choice.values().get(Random().nextInt(Game.Result.values().size))
        val datePlayed = Date()
        val gameResult = getGameResult(botChoice, userChoice)

        val game = Game(datePlayed, botChoice, userChoice, gameResult)
        setResult(game)
    }

    private fun setResult(game: Game){
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }

        // set the picture
        ivComputerChoice.setImageResource(Game.pictureMap[game.computerMove]!!)
        ivUserChoice.setImageResource(Game.pictureMap[game.playerMove]!!)
        when(game.gameResult){
            Game.Result.LOST -> {
                tvResult.setText(R.string.lost)
                losses++
            }
            Game.Result.DRAW -> {
                tvResult.setText(R.string.draw)
                draws++
            }
            Game.Result.WIN -> {
                tvResult.setText(R.string.win)
                wins++
            }
        }
        tvStatistics.text = getString(R.string.statistics, wins.toString(), draws.toString(), losses.toString())
    }

    private fun getGameResult(botChoice: Game.Choice, userChoice: Game.Choice) : Game.Result{
        // Get the result from the win map
        return when {
            botChoice == userChoice -> Game.Result.DRAW
            Game.winMap[userChoice] == botChoice -> Game.Result.WIN
            else -> Game.Result.LOST
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_game_history -> {
                startGameHistoryActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            HISTORY_REQUEST_CODE-> {
                loadStatistics()
            }
        }
    }

    private fun startGameHistoryActivity(){
        // Create an intent for the game history activity (Abstract description of the operation)
        val intent = Intent(this, GameHistoryActivity::class.java)
        // Start the GameHistoryActivity activity
        startActivityForResult(intent, HISTORY_REQUEST_CODE)
    }
}
