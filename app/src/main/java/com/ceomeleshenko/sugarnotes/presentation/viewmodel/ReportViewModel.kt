package com.ceomeleshenko.sugarnotes.presentation.viewmodel

import androidx.compose.runtime.mutableFloatStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceomeleshenko.sugarnotes.Note
import com.ceomeleshenko.sugarnotes.data.NoteRepository
import com.ceomeleshenko.sugarnotes.data.models.InsulinType
import com.ceomeleshenko.sugarnotes.presentation.ui.components.ReportRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

class ReportViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())

    var lowestGlucoseValue = mutableFloatStateOf(5f)
    var highestGlucoseValue = mutableFloatStateOf(5f)

    init {
        getMonthlyRecords()
    }

    private fun getValuesByRange(startDate: String, endDate: String) {
        viewModelScope.launch {
            noteRepository.selectNotesByRange(startDate, endDate).collect { notes ->
                _notes.value = notes
            }
        }
    }

    fun getMonthlyRecords(): List<ReportRecord> {
        val firstDayOfMonth = LocalDate.now().withDayOfMonth(1)
        val lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())
        getValuesByRange(firstDayOfMonth.toString(), lastDayOfMonth.toString())

        val reportRecords = mutableListOf<ReportRecord>()

        lowestGlucoseValue.floatValue = _notes.value[0].glucose.toFloat()
        highestGlucoseValue.floatValue = _notes.value[0].glucose.toFloat()

        _notes.value.forEach { note ->
            note.apply {
                reportRecords.add(
                    ReportRecord(
                        day = date,
                        time = time,
                        glucose = glucose.toFloat(),
                        food = food.toInt(),
                        shortInsulin = if (insulin_type == InsulinType.SHORT.toString()) insulin.toInt() else 0,
                        longInsulin = if (insulin_type == InsulinType.LONG.toString()) insulin.toInt() else 0
                    )
                )
                if (glucose.toFloat() < lowestGlucoseValue.floatValue) {
                    lowestGlucoseValue.floatValue = glucose.toFloat()
                }
                if (glucose.toFloat() > highestGlucoseValue.floatValue) {
                    highestGlucoseValue.floatValue = glucose.toFloat()
                }
            }
        }

        return reportRecords
    }
}