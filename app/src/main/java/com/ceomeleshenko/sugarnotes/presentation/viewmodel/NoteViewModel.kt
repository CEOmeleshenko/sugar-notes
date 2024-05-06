package com.ceomeleshenko.sugarnotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceomeleshenko.sugarnotes.data.NoteRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val notes = noteRepository.selectNotesByDate(LocalDate.now().toString())

    fun insertNote() {
        viewModelScope.launch {
            noteRepository.insertNote(glucose = 5.5, bread_units = 4, insulin = 8)
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            noteRepository.deleteNote(1)
        }
    }

}