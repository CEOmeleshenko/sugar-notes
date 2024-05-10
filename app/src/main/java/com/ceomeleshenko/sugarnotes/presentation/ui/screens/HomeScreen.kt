package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ceomeleshenko.sugarnotes.presentation.ui.components.NotesSection
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
