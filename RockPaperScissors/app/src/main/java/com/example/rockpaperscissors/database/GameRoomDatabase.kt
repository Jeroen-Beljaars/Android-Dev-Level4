package com.example.shoppinglist.Database

import android.content.Context
import androidx.room.*
import com.example.rockpaperscissors.converter.Converters
import com.example.rockpaperscissors.model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameRoomDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var gameRoomDatabaseInstance: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase? {
            if (gameRoomDatabaseInstance == null) {
                synchronized(GameRoomDatabase::class.java) {
                    if (gameRoomDatabaseInstance == null) {
                        gameRoomDatabaseInstance =
                            Room.databaseBuilder(context.applicationContext,GameRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return gameRoomDatabaseInstance
        }
    }

}
