package com.ceomeleshenko.sugarnotes.presentation.viewmodel

import android.icu.text.DecimalFormat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceomeleshenko.sugarnotes.Note
import com.ceomeleshenko.sugarnotes.data.NoteRepository
import com.ceomeleshenko.sugarnotes.data.models.InsulinType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    var averageGlucose by mutableFloatStateOf(0.0f)
    var averageFood by mutableFloatStateOf(0.0f)
    var averageInsulin by mutableFloatStateOf(0.0f)

    var dateValue by mutableStateOf(LocalDate.now().toString())
    var showDatePickerDialog by mutableStateOf(false)

    val notes = MutableStateFlow<List<Note>>(emptyList())

    private fun observeNotes() {
        viewModelScope.launch {
            noteRepository.selectNotesByDate(dateValue).collect {
                notes.value = it
                setAverageGlucose()
                setAverageFood()
                setAverageInsulin()
            }
        }
    }

    fun updateDate(newDate: String) {
        dateValue = newDate
        clearAverageValues()
        observeNotes()
    }

    private fun setAverageGlucose() {
        val list = notes.value
        var sum = 0.0f
        list.forEach { note -> sum += note.glucose.toFloat() }
        if (sum != 0.0f) {
            averageGlucose = DecimalFormat("#.#").format(sum / list.count()).toFloat()
        }
    }

    private fun setAverageFood() {
        val list = notes.value
        var sum = 0.0f
        list.forEach { note -> sum += note.food.toFloat() }
        if (sum != 0.0f) averageFood = DecimalFormat("#.#").format(sum / list.count()).toFloat()
    }

    private fun setAverageInsulin() {
        val list = notes.value
        var sum = 0.0f
        list.forEach { note ->
            if (note.insulin_type == InsulinType.SHORT.toString()) {
                sum += note.insulin.toFloat()
            }
        }
        if (sum != 0.0f) averageInsulin = DecimalFormat("#.#").format(sum / list.count()).toFloat()
    }

    private fun clearAverageValues() {
        averageGlucose = 0.0f
        averageFood = 0.0f
        averageInsulin = 0.0f
    }

    init {
        observeNotes()
    }
}