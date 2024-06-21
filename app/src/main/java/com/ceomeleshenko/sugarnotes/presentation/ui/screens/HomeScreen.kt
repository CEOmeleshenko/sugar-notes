package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
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
    val tutorial = remember { mutableStateOf(true) }

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
