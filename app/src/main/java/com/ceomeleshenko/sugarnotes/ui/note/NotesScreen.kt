package com.ceomeleshenko.sugarnotes.ui.note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.data.entity.InsulinTypes
import com.ceomeleshenko.sugarnotes.data.entity.Note

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
private fun NoteItem(note: Note) {
    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .weight(0.8f)
            ) {
                Text(
                    text = note.glucose.toString() + " " + stringResource(R.string.glucose)
                )
                Text(
                    text = note.bread.toString() + " " + stringResource(R.string.bread)
                )
                Text(
                    text = note.insulin.toString() + " " + stringResource(R.string.insulin) + ", " +
                            when (note.insulinType) {
                                InsulinTypes.ULTRA_SHORT -> stringResource(R.string.insulin_type_ultrashort)
                                InsulinTypes.SHORT -> stringResource(R.string.insulin_type_short)
                                InsulinTypes.LONG -> stringResource(R.string.insulin_type_long)
                            }
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