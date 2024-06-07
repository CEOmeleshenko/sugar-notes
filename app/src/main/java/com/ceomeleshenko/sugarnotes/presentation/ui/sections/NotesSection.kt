package com.ceomeleshenko.sugarnotes.presentation.ui.sections

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.Note
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.data.models.InsulinType
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.NotesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotesSection(
    viewModel: NotesViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val notes by viewModel.notes.collectAsState()

    if (notes.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.TopCenter),
                text = "Нет записей"
            )
        }
    }

    LazyColumn(
        modifier = modifier.animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        items(notes) { note ->
            NoteItem(note)
        }
    }
}

@Composable
private fun NoteItem(
    note: Note
) {

//    val swipeThreshold = 100.dp TODO
//    val swipePercentage = remember { mutableFloatStateOf(0f) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
//            .pointerInput(Unit) {
//                detectDragGestures(
//                    onDragEnd = {
//                        if (swipePercentage.floatValue > 0.5f) {
//                            Log.d("TAG", "NoteItem: deleted")
//                        }
//                        swipePercentage.floatValue = 0f
//                    },
//                    onDrag = { change, dragAmount ->
//                        change.consume()
//                        val newSwipePercentage = dragAmount.x / swipeThreshold.value
//                        swipePercentage.floatValue = newSwipePercentage.coerceIn(0f, 1f)
//                    }
//                )
//            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp, 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                color = MaterialTheme.colorScheme.background,
                style = Typography.bodyLarge,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(12.dp, 4.dp),
                fontWeight = FontWeight.Medium,
                text = if (note.glucose != 0.0) {
                    note.glucose.toString() + " " + stringResource(R.string.unit_glucose)
                } else {
                    stringResource(id = R.string.unit_no_data)
                }
            )
            Text(
                color = MaterialTheme.colorScheme.background,
                style = Typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(12.dp, 4.dp),
                text = note.time
            )
        }
        if (note.insulin != 0L) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp, 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    color = MaterialTheme.colorScheme.background,
                    style = Typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(12.dp, 4.dp),
                    text = when (note.insulin_type) {
                        InsulinType.SHORT.toString() -> stringResource(R.string.insulin_type_short)
                        InsulinType.LONG.toString() -> stringResource(R.string.insulin_type_long)
                        else -> ""
                    }
                )
                Text(
                    color = MaterialTheme.colorScheme.background,
                    style = Typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(12.dp, 4.dp),
                    text = note.insulin.toString() + " " + stringResource(R.string.unit_insulin)
                )
            }
        }
        if (note.food != 0L) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp, 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    color = MaterialTheme.colorScheme.background,
                    style = Typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(12.dp, 4.dp),
                    text = note.food.toString() + " " + stringResource(R.string.unit_food)
                )
            }
        }
    }
}