package com.ceomeleshenko.sugarnotes.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceomeleshenko.sugarnotes.data.NoteRepository
import com.ceomeleshenko.sugarnotes.data.models.InsulinType
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class AddNoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    var glucoseValue by mutableDoubleStateOf(5.0)
    var insulinValue by mutableLongStateOf(0L)
    var insulinType by mutableStateOf(InsulinType.SHORT)
    var foodValue by mutableLongStateOf(0L)
    var timeValue by mutableStateOf(LocalTime.now().withSecond(0).withNano(0).toString())
    var dateValue by mutableStateOf(LocalDate.now().toString())

    var showTimePickerDialog by mutableStateOf(false)
    var showDatePickerDialog by mutableStateOf(false)

    fun insertNote() {
        viewModelScope.launch {
            noteRepository.insertNote(
                glucose = glucoseValue,
                food = foodValue,
                insulin = insulinValue,
                insulin_type = insulinType.toString(),
                date = dateValue,
                time = timeValue
            )
        }
    }
}