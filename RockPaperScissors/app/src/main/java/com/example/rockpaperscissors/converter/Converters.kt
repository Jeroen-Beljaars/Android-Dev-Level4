package com.example.rockpaperscissors.converter

import androidx.room.TypeConverter
import com.example.rockpaperscissors.model.Game
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun resultToInt(gameResult: Game.Result?): Int? {
        return gameResult?.result
    }

    @TypeConverter
    fun fromInt(resultInt: Int?): Game.Result? {
        return Game.Result.values().associateBy(Game.Result::result)[resultInt]
    }

    @TypeConverter
    fun choiceToInt(choice: Game.Choice?): Int? {
        return choice?.choice
    }

    @TypeConverter
    fun fromChoice(resultInt: Int?): Game.Choice? {
        return Game.Choice.values().associateBy(Game.Choice::choice)[resultInt]
    }
}