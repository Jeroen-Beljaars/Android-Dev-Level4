package com.example.shoppinglist.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.rockpaperscissors.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM game_table order by date_played DESC")
    suspend fun getGameHistory(): List<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM game_table")
    suspend fun deleteGameHistory()

    @Query("SELECT COUNT(*) FROM game_table WHERE game_result = -1")
    suspend fun getAmountOfLosses(): Int

    @Query("SELECT COUNT(*) FROM game_table WHERE game_result = 0")
    suspend fun getAmountOfDraws(): Int

    @Query("SELECT COUNT(*) FROM game_table WHERE game_result = -1")
    suspend fun getAmountOfWins(): Int
}
