package com.example.shoppinglist.Database

import android.content.Context
import com.example.rockpaperscissors.model.Game

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun getGameHistory(): List<Game> = gameDao.getGameHistory()

    suspend fun insertGame(game: Game) = gameDao.insertGame(game)

    suspend fun deleteGame(game: Game) = gameDao.deleteGame(game)

    suspend fun deleteGameHistory() = gameDao.deleteGameHistory()

    suspend fun getAmountOfLosses() = gameDao.getAmountOfLosses()

    suspend fun getAmountOfDraws() = gameDao.getAmountOfDraws()

    suspend fun getAmountOfWins() = gameDao.getAmountOfWins()
}
