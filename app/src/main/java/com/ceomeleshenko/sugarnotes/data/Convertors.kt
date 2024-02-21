package com.ceomeleshenko.sugarnotes.data

import androidx.room.TypeConverter
import java.time.LocalDateTime

class Convertors {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofEpochSecond(it, 0, java.time.ZoneOffset.UTC) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.toEpochSecond(java.time.ZoneOffset.UTC)
    }
}