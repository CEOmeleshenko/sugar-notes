package com.ceomeleshenko.sugarnotes.data

import com.ceomeleshenko.sugarnotes.Database
import com.ceomeleshenko.sugarnotes.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime

class NoteDataSource(database: Database) {

    private val queries = database.noteQueries

    suspend fun getNoteById(id: Long): Note? {
        TODO("Not yet implemented")
    }

    fun getNotesByDate(date: Long): Flow<List<Note>> {
        TODO("Not yet implemented")
    }

    suspend fun insertNote(
        glucose: Double = 0.0,
        bread_units: Long = 0,
        insulin: Long = 0,
        insulin_type: String = InsulinType.SHORT.toString(),
        date: String = LocalDate.now().toString(),
        time: String = LocalTime.now().toString()
    ) {
        withContext(Dispatchers.IO) {
            queries.insertNote(glucose, bread_units, insulin, insulin_type, date, time)
        }
    }
}