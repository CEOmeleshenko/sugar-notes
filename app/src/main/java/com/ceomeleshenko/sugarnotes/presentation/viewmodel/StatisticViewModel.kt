package com.ceomeleshenko.sugarnotes.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceomeleshenko.sugarnotes.Note
import com.ceomeleshenko.sugarnotes.data.NoteRepository
import com.ceomeleshenko.sugarnotes.data.models.ChartType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale

class StatisticViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> get() = _notes

    var chartType by mutableStateOf(ChartType.WEEKLY)
    val chartValues = MutableStateFlow<List<Float>>(emptyList())
    val chartAxis = MutableStateFlow<List<String>>(emptyList())

    var weeklyTasks by mutableIntStateOf(0)


    init {
        getWeeklyValues()
    }

    private fun getValuesByRange(startDate: String, endDate: String) {
        viewModelScope.launch {
            noteRepository.selectNotesByRange(startDate, endDate).collect { notes ->
                _notes.value = notes
            }
        }
    }

    private fun getDailyValues() {
        val day = LocalDate.now()
        getValuesByRange(day.toString(), day.toString())
        if (notes.value.isNotEmpty()) {
            chartValues.value = notes.value.map { it.glucose.toFloat() }.toList()
            chartAxis.value = notes.value.map { it.time }.toList()
        }
        else {
            chartValues.value = listOf(0f)
            chartAxis.value = listOf("")
        }
    }

    private fun getWeeklyValues() {
        val startOfWeek = LocalDate.now().with(DayOfWeek.MONDAY)
        val endOfWeek = startOfWeek.plusDays(6)
        getValuesByRange(startOfWeek.toString(), endOfWeek.toString())

        val groupedByDay = notes.value.groupBy { LocalDate.parse(it.date).dayOfWeek }
        chartValues.value = DayOfWeek.entries.map { dayOfWeek ->
            val notesForDay = groupedByDay.filterKeys { dayOfWeek == it }
            if (notesForDay.isNotEmpty()) {
                notesForDay.values.flatten().map { it.glucose.toFloat() }.average().toBigDecimal()
                    .setScale(1, RoundingMode.UP).toFloat()
            } else {
                0f
            }
        }

        val weekDays =
            DayOfWeek.entries.map { it.getDisplayName(TextStyle.SHORT, Locale.getDefault()) }
        chartAxis.value = weekDays

        weeklyTasks = groupedByDay.size
    }

    private fun getMonthlyValues() {
        val firstDayOfMonth = LocalDate.now().withDayOfMonth(1)
        val lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())
        getValuesByRange(firstDayOfMonth.toString(), lastDayOfMonth.toString())

        val daysOfMonth = generateSequence(firstDayOfMonth) { it.plusDays(1) }.takeWhile {
            !it.isAfter(lastDayOfMonth)
        }.toList()
        val averageGlucosePerDay = daysOfMonth.map { day ->
            val notesForDay = notes.value.filter { LocalDate.parse(it.date) == day }
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

    fun changeChartType(newChartType: ChartType) {
        chartType = newChartType
        when (chartType) {
            ChartType.DAILY -> getDailyValues()
            ChartType.WEEKLY -> getWeeklyValues()
            ChartType.MONTHLY -> getMonthlyValues()
        }
    }
}