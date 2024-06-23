package com.ceomeleshenko.sugarnotes.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun NotesTable(reportRecords: List<ReportRecord>) {
    val groupedRecords = reportRecords.groupBy { it.day }.toList().chunked(4)

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.LightGray.copy(alpha = 0.1f))
    ) {
        groupedRecords.forEach { dayGroup ->
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(bottom = 8.dp)
            ) {
                dayGroup.forEach { (day, dayRecords) ->

                    val longInsulinValue = dayRecords.find { it.longInsulin != 0 }?.longInsulin ?: 0

                    Column(
                        modifier = Modifier.padding(horizontal = 4.dp)
                    ) {
                        DayHeader(day = day)
                        Row(Modifier.width(220.dp)) {
                            Text(
                                text = "время",
                                modifier = Modifier.width(60.dp),
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = "сахар",
                                modifier = Modifier.weight(0.4f),
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = "инсулин",
                                modifier = Modifier.weight(0.4f),
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = "еда",
                                modifier = Modifier.weight(0.4f),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        dayRecords.forEach { record ->
                            RecordRow(reportRecord = record)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "длинный инсулин: $longInsulinValue ед.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                repeat(4 - dayGroup.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
        Column {
            Row {
                Box(modifier = Modifier.background(Color(0xFFE0F7FA)).size(20.dp))
                Text(text = " - гипогликемия (уровень сахара < 4.0) ")
            }
            Row {
                Box(modifier = Modifier.background(Color(0xFFE8F5E9)).size(20.dp))
                Text(text = " - нормальный уровень сахара")
            }
            Row {
                Box(modifier = Modifier.background(Color(0xFFFFEBEE)).size(20.dp))
                Text(text = " - гипергликемия (уровень сахара > 12.0) ")
            }
        }
    }
}

@Composable
private fun DayHeader(day: String) {
    val date = LocalDate.parse(day)
    val text = date.dayOfMonth.toString() + " " + date.month.getDisplayName(
        TextStyle.FULL,
        Locale.getDefault()
    )

    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}

@Composable
private fun RecordRow(reportRecord: ReportRecord) {
    val backgroundColor = when {
        reportRecord.glucose < 4.0 -> Color(0xFFE0F7FA)
        reportRecord.glucose > 12.0 -> Color(0xFFFFEBEE)
        else -> Color(0xFFE8F5E9)
    }

    Row(
        modifier = Modifier
            .width(220.dp)
            .background(backgroundColor)
            .padding(4.dp)
    ) {
        Text(
            text = reportRecord.time,
            modifier = Modifier.width(60.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = reportRecord.glucose.toString(),
            modifier = Modifier.weight(0.4f),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = reportRecord.shortInsulin.toString(),
            modifier = Modifier.weight(0.4f),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = reportRecord.food.toString(),
            modifier = Modifier.weight(0.4f),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

data class ReportRecord(
    val day: String,
    val time: String,
    val glucose: Float,
    val shortInsulin: Int = 0,
    val food: Int = 0,
    val longInsulin: Int = 0
)