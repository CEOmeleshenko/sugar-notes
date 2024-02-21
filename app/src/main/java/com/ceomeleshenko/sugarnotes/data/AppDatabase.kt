package com.ceomeleshenko.sugarnotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ceomeleshenko.sugarnotes.data.dao.NotesDao
import com.ceomeleshenko.sugarnotes.data.entity.Note

@Database(entities = [Note::class], exportSchema = false, version = 1)
@TypeConverters(Convertors::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}
