package com.ceomeleshenko.sugarnotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceomeleshenko.sugarnotes.data.NoteDataSource
import kotlinx.coroutines.launch

class NoteViewModel(private val noteDataSource: NoteDataSource) : ViewModel() {
    fun insertNote() {
        viewModelScope.launch {
            noteDataSource.insertNote(
                glucose = 5.5,
                bread_units = 4,
                insulin = 8
            )
        }
    }
}