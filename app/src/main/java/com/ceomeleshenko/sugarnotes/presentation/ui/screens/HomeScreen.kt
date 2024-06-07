package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.presentation.ui.components.DateSelectionDialog
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.AverageValuesSection
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.CalendarSection
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.DailyTasksSection
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.NotesSection
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.NotesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: NotesViewModel = koinViewModel()
) {
    if (viewModel.showDatePickerDialog) {
        DateSelectionDialog(
            onDateSelected = { viewModel.updateDate(it) },
            onDismissRequest = { viewModel.showDatePickerDialog = false }
        )
    }


    Column {
        Row(modifier = Modifier.padding(start = 16.dp, top = 10.dp, end = 16.dp, bottom = 5.dp)) {
            CalendarSection()
            DailyTasksSection()
        }
        AverageValuesSection(modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp))
        NotesSection()
    }
}
