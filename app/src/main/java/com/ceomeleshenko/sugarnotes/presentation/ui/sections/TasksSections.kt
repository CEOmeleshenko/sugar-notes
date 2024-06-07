package com.ceomeleshenko.sugarnotes.presentation.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.data.models.InsulinType
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.NotesViewModel
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.StatisticViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DailyTasksSection(
    viewModel: NotesViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val notes by viewModel.notes.collectAsState()
    val dailyTasks = viewModel.dailyTasks

    Box(
        modifier = modifier
            .padding(start = 8.dp)
//            .width(180.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = stringResource(R.string.daily_tasks))
            Text(
                text = "› ${stringResource(R.string.daily_task_1)}: $dailyTasks/4",
                textDecoration = if (dailyTasks >= 4) TextDecoration.LineThrough
                else null,
                style = Typography.labelMedium

            )
            Text(
                text = "› ${stringResource(R.string.daily_task_2)}",
                textDecoration = if (notes.find { it.insulin_type == InsulinType.LONG.toString() } != null)
                    TextDecoration.LineThrough
                else null,
                style = Typography.labelMedium
            )
        }
    }
}

@Composable
fun WeeklyTasksSection(
    viewModel: StatisticViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val weeklyTasks = viewModel.weeklyTasks

    Box(
        modifier = modifier
//            .width(180.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = stringResource(R.string.weekly_tasks))
            Text(
                text = "› ${stringResource(R.string.weekly_task_1)}: $weeklyTasks/7",
                style =
                if (weeklyTasks >= 7) TextStyle(textDecoration = TextDecoration.LineThrough)
                else LocalTextStyle.current
            )
        }
    }
}