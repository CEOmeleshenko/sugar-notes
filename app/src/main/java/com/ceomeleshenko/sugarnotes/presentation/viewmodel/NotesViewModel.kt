package com.ceomeleshenko.sugarnotes.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceomeleshenko.sugarnotes.Note
import com.ceomeleshenko.sugarnotes.data.NoteRepository
import com.ceomeleshenko.sugarnotes.data.models.InsulinType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.time.LocalDate

class NotesViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    var dailyTasks by mutableIntStateOf(0)
    var averageGlucose by mutableDoubleStateOf(0.0)
    var averageFood by mutableFloatStateOf(0.0f)
    var averageInsulin by mutableFloatStateOf(0.0f)

    var dateValue by mutableStateOf(LocalDate.now().toString())
    var showDatePickerDialog by mutableStateOf(false)

    val notes = MutableStateFlow<List<Note>>(emptyList())

    init {
        observeNotes()
    }

    fun updateDate(newDate: String) {
        dateValue = newDate
        clearAverageValues()
        observeNotes()
    }

    private fun observeNotes() {
        viewModelScope.launch {
            noteRepository.selectNotesByDate(dateValue).collect {
                notes.value = it
                setDailyTasks()
                setAverageGlucose()
                setAverageFood()
                setAverageInsulin()
            }
        }
    }

    private fun setDailyTasks() {
        dailyTasks = notes.value.size
    }

    private fun setAverageGlucose() {
        val list = notes.value
        var sum = 0.0
        list.forEach { note -> sum += note.glucose }
        if (sum != 0.0) {
            val roundedNumber = (sum / (list.count { it.glucose != 0.0 }))
                .toBigDecimal()
                .setScale(1, RoundingMode.UP)
                .toDouble()
            averageGlucose = roundedNumber
        }
    }

    private fun setAverageFood() {
        val list = notes.value
        var sum = 0.0f
        list.forEach { note -> sum += note.food.toFloat() }
        if (sum != 0.0f) averageFood = sum / list.count { it.food.toFloat() != 0.0f }
    }

    private fun setAverageInsulin() {
        val list = notes.value
        var sum = 0.0f
        list.forEach { note ->
            if (note.insulin_type == InsulinType.SHORT.toString()) {
                sum += note.insulin.toFloat()
            }
        }
        if (sum != 0.0f) averageInsulin = sum / list.count {
            it.insulin.toFloat() != 0.0f && it.insulin_type == InsulinType.SHORT.toString()
        }
    }

    private fun clearAverageValues() {
        averageGlucose = 0.0
        averageFood = 0.0f
        averageInsulin = 0.0f
    }
}