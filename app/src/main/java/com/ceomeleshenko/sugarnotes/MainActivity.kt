package com.ceomeleshenko.sugarnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.ceomeleshenko.sugarnotes.data.DatabaseProvider
import com.ceomeleshenko.sugarnotes.data.NoteDataSource
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.SugarNotesTheme
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SugarNotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val database = DatabaseProvider.getDatabase(applicationContext)
                    val noteDataSource = NoteDataSource(database)
                    val noteViewModel = NoteViewModel(noteDataSource)
                    Row {
                        Button(onClick = {
                            noteViewModel.insertNote()
                        }) {
                            Text(text = "Add note")
                        }
                    }
                }
            }
        }
    }
}