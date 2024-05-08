package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    val notes by viewModel.notes.collectAsState(initial = emptyList())

    Column {
        Row {
            Button(onClick = {
                viewModel.insertNote()
            }) {
                Text(text = "Add note")
            }
            Button(onClick = {
                viewModel.deleteNote()
            }) {
                Text(text = "Delete note")
            }
        }
        NotesSection()
    }
}
