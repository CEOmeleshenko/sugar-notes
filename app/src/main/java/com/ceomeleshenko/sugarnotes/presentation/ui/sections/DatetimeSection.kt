package com.ceomeleshenko.sugarnotes.presentation.ui.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.AddNoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DatetimeSection(
    viewModel: AddNoteViewModel = koinViewModel()
) {
    Row {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .width(120.dp)
                .height(50.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { viewModel.showTimePickerDialog = true }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = viewModel.timeValue,
                    fontWeight = FontWeight.Medium,
                    style = Typography.titleMedium
                )
            }
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { viewModel.showDatePickerDialog = true }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = viewModel.dateValue,
                    fontWeight = FontWeight.Medium,
                    style = Typography.titleMedium
                )
            }
        }
    }
}