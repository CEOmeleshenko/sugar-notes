package com.ceomeleshenko.sugarnotes.ui

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ceomeleshenko.sugarnotes.data.entity.Note
import com.ceomeleshenko.sugarnotes.ui.note.NoteViewModel
import com.ceomeleshenko.sugarnotes.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.ui.theme.GreenPrimary

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
        ) {
            Text(text = "Новая запись")

            Button(onClick = {
                viewModel.insertNote(Note(glucose = 7, bread = 3, insulin = 4))
            }) {
                Text(text = "Добавить запись")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddNoteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Close",
                tint = Color.White,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable { }
            )
        }

        Text(
            text = "Новая запись",
            style = Typography.titleLarge,
            modifier = Modifier
                .padding(16.dp, 0.dp)
        )

        Row(
            modifier = Modifier.padding(16.dp),
            Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(170.dp)
                    .height(156.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {

            }
            Box(
                modifier = Modifier
                    .width(148.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {

            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(90.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {

            }
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Box(
                modifier = Modifier
                    .width(334.dp)
                    .height(168.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {

            }
        }

        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Добавить запись")
        }
    }
}