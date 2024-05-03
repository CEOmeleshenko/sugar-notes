package com.ceomeleshenko.sugarnotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceomeleshenko.sugarnotes.data.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    fun insertNote() {
        viewModelScope.launch {
            noteRepository.insertNote(
                glucose = 5.5,
                bread_units = 4,
                insulin = 8
            )
        }
    }
}