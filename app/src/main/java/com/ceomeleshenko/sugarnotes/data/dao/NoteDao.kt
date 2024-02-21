package com.ceomeleshenko.sugarnotes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ceomeleshenko.sugarnotes.data.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM Note WHERE id = :id")
    suspend fun getNoteById(id: Int) : Note

    @Query("SELECT * FROM Note")
    fun getAllNotes(): Flow<List<Note>>
}