package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.ChartSection
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.WeeklyTasksSection

@Composable
fun StatisticScreen() {
    Column {
        ChartSection(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))
        WeeklyTasksSection(modifier = Modifier.padding(horizontal = 16.dp))
    }
}