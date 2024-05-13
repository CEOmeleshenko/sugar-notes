package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.ceomeleshenko.sugarnotes.presentation.ui.components.DateSelectionDialog
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.AverageValuesSection
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.CalendarSection
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.NotesSection
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: NoteViewModel = koinViewModel()
) {
    if (viewModel.showDatePickerDialog) {
        DateSelectionDialog(
            onDateSelected = { viewModel.updateDate(it) },
            onDismissRequest = { viewModel.showDatePickerDialog = false }
        )
    }


    Column {
        Row {
            CalendarSection()
        }
        AverageValuesSection()
        NotesSection()
    }
}
