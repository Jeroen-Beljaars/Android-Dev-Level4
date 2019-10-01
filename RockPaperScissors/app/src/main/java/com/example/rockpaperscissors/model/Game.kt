package com.example.rockpaperscissors.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rockpaperscissors.R
import java.util.*

@Entity(tableName = "game_table")
data class Game (
    @ColumnInfo(name = "date_played")
    var datePlayed: Date,

    @ColumnInfo(name = "computer_move")
    var computerMove: Game.Choice,

    @ColumnInfo(name = "player_move")
    var playerMove: Game.Choice,

    @ColumnInfo(name = "game_result")
    var gameResult: Game.Result,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Long? = null
) {
    companion object {
        // Specifies which choice wins from what
        val winMap = mapOf(
            Choice.ROCK to Choice.SCISSORS,
            Choice.PAPER to Choice.ROCK,
            Choice.SCISSORS to Choice.PAPER
        )

        // Specifies which picture belongs to which choice
        val pictureMap = mapOf(
            Choice.ROCK to R.drawable.rock,
            Choice.PAPER to R.drawable.paper,
            Choice.SCISSORS to R.drawable.scissors
        )
    }

    enum class Choice(val choice: Int) {
        ROCK(0),
        PAPER(1),
        SCISSORS(2),
    }

    enum class Result(val result: Int) {
        WIN(1),
        DRAW(0),
        LOST(-1)
    }
}