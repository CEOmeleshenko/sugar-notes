package com.ceomeleshenko.sugarnotes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ceomeleshenko.sugarnotes.data.entity.Note
import com.ceomeleshenko.sugarnotes.ui.note.NoteViewModel
import com.ceomeleshenko.sugarnotes.ui.note.NotesScreen

@Composable
fun HomeScreen(
    viewModel: NoteViewModel,
    navController: NavController
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = "Profile",
                Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable {
                        navController.navigate("Profile")
                    }
            )
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Profile",
                Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable {
                        navController.navigate("AddNote")
                    }
            )

        }

        Button(onClick = {
            viewModel.insertNote(Note(glucose = 7, bread = 3, insulin = 4, ))
        }) {
            Text(text = "Добавить запись")
        }

        Divider(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .height(2.dp),
            color = Color.Black
        )

        NotesScreen(viewModel)
    }
}