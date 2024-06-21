package com.ceomeleshenko.sugarnotes.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography

@Composable
fun NotesTable(records: List<Record>) {
    val groupedRecords = records.groupBy { it.day }
//    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .verticalScroll(scrollState)
    ) {
        groupedRecords.forEach { (day, dayRecords) ->
            DayHeader(day = day)
            dayRecords.forEach { 
                RecordRow(record = it)
            }
        }
    }
}

@Composable
private fun DayHeader(day: String) {
    Text(
        text = day,
        style = Typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}

@Composable
private fun RecordRow(record: Record) {
    val backgroundColor = when {
        record.glucoseLevel < 4.0 -> Color(0xFFE0F7FA)
        record.glucoseLevel > 12.0 -> Color(0xFFFFEBEE)
        else -> Color(0xFFE8F5E9)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(4.dp)
    ) {
        Text(
            text = record.time,
            modifier = Modifier.weight(1f),
            style = Typography.bodyMedium
        )
        Text(
            text = record.glucoseLevel.toString(),
            modifier = Modifier.weight(1f),
            style = Typography.bodyMedium
        )
        Text(
            text = record.insulinAmount.toString(),
            modifier = Modifier.weight(1f),
            style = Typography.bodyMedium
        )
        Text(
            text = record.foodAmount.toString(),
            modifier = Modifier.weight(1f),
            style = Typography.bodyMedium
        )
    }
}

data class Record(
    val day: String,
    val time: String,
    val glucoseLevel: Float,
    val insulinAmount: Int,
    val foodAmount: Int
)