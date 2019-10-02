package com.example.rockpaperscissors.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.model.Game
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return games.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            // set the picture
            itemView.ivComputerChoice.setImageResource(Game.pictureMap[game.computerMove]!!)
            itemView.ivUserChoice.setImageResource(Game.pictureMap[game.playerMove]!!)
            itemView.tvTimestamp.text = game.datePlayed.toString()
            when(game.gameResult){
                Game.Result.LOST -> itemView.tvResult.setText(R.string.lost)
                Game.Result.DRAW -> itemView.tvResult.setText(R.string.draw)
                Game.Result.WIN -> itemView.tvResult.setText(R.string.win)
            }
        }

    }
}
