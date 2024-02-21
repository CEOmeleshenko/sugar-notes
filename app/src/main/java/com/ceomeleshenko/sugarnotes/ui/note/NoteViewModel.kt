package com.ceomeleshenko.sugarnotes.ui.note

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.ceomeleshenko.sugarnotes.data.AppDatabase
import com.ceomeleshenko.sugarnotes.data.entity.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val database: AppDatabase = Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java,
        "notes_database"
    ).build()

    val notes = database.notesDao().getAllNotes()

    fun insertNote(note: Note) {
        viewModelScope.launch {
            database.notesDao().insertNote(note)
        }
    }
}