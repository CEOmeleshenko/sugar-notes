package com.ceomeleshenko.sugarnotes.ui.note

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ceomeleshenko.sugarnotes.data.entity.Note

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun Notes() {
//    val notes = listOf(
//        Note(1, 5, 5, 5),
//        Note(2, 4, 4, 4)
//    )
//    NotesScreen(notes)
//}

@Composable
fun NotesScreen(viewModel: NoteViewModel) {
    val notes = viewModel.notes.collectAsState(initial = emptyList())

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(notes.value) { note ->
            NoteItem(note)
        }
    }
}


@Composable
fun NoteItem(note: Note) {
//  TODO заменить хардкод на значения
    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .weight(0.8f)
            ) {
                Text(
                    text = note.glucose.toString() + "ммоль/л",
                )
                Text(
                    text = note.bread.toString() + "ХЕ",
                )
                Text(
                    text = note.insulin.toString() + "ЕД",
                )
            }
            Column(
                modifier = Modifier
                    .padding(8.dp, 8.dp)
                    .weight(0.2f),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "${
                        if (note.dateTime.hour >= 10) note.dateTime.hour
                        else "0${note.dateTime.hour}"
                    }:${
                        if (note.dateTime.minute >= 10) note.dateTime.minute
                        else "0${note.dateTime.minute}"
                    }",
                    modifier = Modifier.align(Alignment.End)
                )

            }
        }
    }
}