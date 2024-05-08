package com.ceomeleshenko.sugarnotes.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ceomeleshenko.sugarnotes.Database
import com.ceomeleshenko.sugarnotes.Note
import com.ceomeleshenko.sugarnotes.data.models.InsulinType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime

interface NoteRepository {
    suspend fun selectNoteById(id: Long): Note?

    fun selectNotesByDate(date: String): Flow<List<Note>>

    suspend fun insertNote(
        glucose: Double = 0.0,
        bread_units: Long = 0,
        insulin: Long = 0,
        insulin_type: String = InsulinType.SHORT.toString(),
        date: String = LocalDate.now().toString(),
        time: String = LocalTime.now().withSecond(0).withNano(0).toString()
    )

    suspend fun deleteNote(id: Long)

    suspend fun updateNote(note: Note)
}

class NoteRepositoryImpl(database: Database) : NoteRepository {
    private val queries = database.noteQueries

    override suspend fun selectNoteById(id: Long): Note? {
        return queries.selectNoteById(id).executeAsOneOrNull()
    }

    override fun selectNotesByDate(date: String): Flow<List<Note>> {
        return queries.selectNotesByDate(date).asFlow().mapToList(Dispatchers.IO)
    }

    override suspend fun insertNote(
        glucose: Double,
        bread_units: Long,
        insulin: Long,
        insulin_type: String,
        date: String,
        time: String
    ) {
        withContext(Dispatchers.IO) {
            queries.insertNote(
                glucose, bread_units, insulin, insulin_type, date, time
            )
        }
    }

    override suspend fun deleteNote(id: Long) {
        withContext(Dispatchers.IO) {
            queries.deleteNote(id)
        }
    }

    override suspend fun updateNote(note: Note) {
        withContext(Dispatchers.IO) {
            note.apply {
                queries.updateNote(glucose, bread_units, insulin, insulin_type, date, time, id)
            }
        }
    }
}