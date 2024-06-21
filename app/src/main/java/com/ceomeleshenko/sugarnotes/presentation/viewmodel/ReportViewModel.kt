package com.ceomeleshenko.sugarnotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceomeleshenko.sugarnotes.Note
import com.ceomeleshenko.sugarnotes.data.NoteRepository
import com.ceomeleshenko.sugarnotes.presentation.ui.screens.ReportData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

class ReportViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    private val _reportData = MutableStateFlow(ReportData())
    val reportData: StateFlow<ReportData> get() = _reportData

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val chartValues = MutableStateFlow<List<Float>>(emptyList())
    val chartAxis = MutableStateFlow<List<String>>(emptyList())

    init {
        getMonthlyValues()
    }

    private fun getValuesByRange(startDate: String, endDate: String) {
        viewModelScope.launch {
            noteRepository.selectNotesByRange(startDate, endDate).collect { notes ->
                _notes.value = notes
            }
        }
    }

    private fun getMonthlyValues() {
        val firstDayOfMonth = LocalDate.now().withDayOfMonth(1)
        val lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())
        getValuesByRange(firstDayOfMonth.toString(), lastDayOfMonth.toString())

        val daysOfMonth = generateSequence(firstDayOfMonth) { it.plusDays(1) }.takeWhile {
            !it.isAfter(lastDayOfMonth)
        }.toList()
        val averageGlucosePerDay = daysOfMonth.map { day ->
            val notesForDay = _notes.value.filter { LocalDate.parse(it.date) == day }
            if (notesForDay.isNotEmpty()) {
                notesForDay.map { it.glucose.toFloat() }.average().toBigDecimal()
                    .setScale(1, RoundingMode.UP).toFloat()
            } else {
                0f
            }
        }
        chartValues.value = averageGlucosePerDay

        chartAxis.value = daysOfMonth.map {
            LocalDate.parse(it.toString()).dayOfMonth.toString()
        }.toList()
    }
}