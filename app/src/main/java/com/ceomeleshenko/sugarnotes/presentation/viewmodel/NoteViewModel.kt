package com.ceomeleshenko.sugarnotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceomeleshenko.sugarnotes.data.NoteRepository
import com.ceomeleshenko.sugarnotes.data.models.InsulinType
import kotlinx.coroutines.launch
import java.time.LocalDate

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val notes = noteRepository.selectNotesByDate(LocalDate.now().toString())

    fun insertNote(
        glucose: Double = 0.0,
        food: Long = 0L,
        insulin: Long = 0L,
        insulinType: String = InsulinType.SHORT.toString(),
    ) {
        viewModelScope.launch {
            noteRepository.insertNote(
                glucose = glucose,
                food = food,
                insulin = insulin,
                insulin_type = insulinType
            )
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            noteRepository.deleteNote(1)
        }
    }

}