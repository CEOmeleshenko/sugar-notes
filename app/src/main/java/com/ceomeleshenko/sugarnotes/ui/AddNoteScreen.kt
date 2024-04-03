package com.ceomeleshenko.sugarnotes.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ceomeleshenko.sugarnotes.ui.note.NoteViewModel
import com.ceomeleshenko.sugarnotes.ui.note.NotesScreen

@Composable
fun AddNoteScreen(
    viewModel: NoteViewModel,
    navController: NavController
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Close",
                Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable {
                        navController.navigate("Home")
                    }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, Color.Black, RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
        ) {
            Text(text = "Новая запись")


            
            Button(onClick = { 
//                viewModel.insertNote()
            }) {
                Text(text = "Добавить")
            }
        }
    }
}

