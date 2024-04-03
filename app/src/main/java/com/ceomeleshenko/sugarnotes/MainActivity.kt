package com.ceomeleshenko.sugarnotes

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ceomeleshenko.sugarnotes.data.entity.Note
import com.ceomeleshenko.sugarnotes.ui.AddNoteScreen
import com.ceomeleshenko.sugarnotes.ui.HomeScreen
import com.ceomeleshenko.sugarnotes.ui.ProfileScreen
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
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = "Home") {
                            composable("Home") {
                                HomeScreen(viewModel, navController)
                            }
                            composable("AddNote") {
                                AddNoteScreen(viewModel, navController)
                            }
                            composable("Profile") {
                                ProfileScreen(navController)
                            }
                        }

                    }
                }
            }
        }
    }
}