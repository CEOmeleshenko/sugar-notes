package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.ChartSection
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.WeeklyTasksSection

@Composable
fun StatisticScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            Text(
                text = "Уровни сахара крови",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            ChartSection(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
            WeeklyTasksSection(modifier = Modifier.padding(horizontal = 16.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(onClick = { navController.navigate("Report") }) {
                Text(text = "Составить отчет")
            }
        }
    }
}