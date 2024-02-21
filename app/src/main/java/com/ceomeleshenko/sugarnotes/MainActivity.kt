package com.ceomeleshenko.sugarnotes

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ceomeleshenko.sugarnotes.data.entity.Note
import com.ceomeleshenko.sugarnotes.ui.note.NoteViewModel
import com.ceomeleshenko.sugarnotes.ui.note.NotesScreen
import com.ceomeleshenko.sugarnotes.ui.theme.SugarNotesAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = NoteViewModel(application)

        setContent {
            SugarNotesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Button(onClick = {
                            viewModel.insertNote(Note(glucose = 7, bread = 3, insulin = 4))
                        }) {
                            Text(text = "Добавить запись")
                        }
                        NotesScreen(viewModel)
                    }
                }
            }
        }
    }
}