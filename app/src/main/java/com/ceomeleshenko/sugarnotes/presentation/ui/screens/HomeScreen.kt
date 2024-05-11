package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.NotesSection
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: NoteViewModel = koinViewModel()
) {
    Column {
        NotesSection()
    }
}
