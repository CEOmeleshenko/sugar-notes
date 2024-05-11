package com.ceomeleshenko.sugarnotes.presentation.ui.sections

import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.presentation.ui.components.HorizontalNumberPicker
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.AddNoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GlucoseSection(viewModel: AddNoteViewModel = koinViewModel()) {
    Column {
        Text(
            text = stringResource(R.string.add_note_section_glucose),
            style = Typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = viewModel.glucoseValue.toFloat()
                            .toString() + " " + stringResource(R.string.unit_glucose),
                        fontWeight = FontWeight.Medium,
                        style = Typography.titleLarge
                    )
                }
                Row {
                    HorizontalNumberPicker(
                        maxNumber = 34.0,
                        selectedNumber = viewModel.glucoseValue,
                        modifier = Modifier
                            .width(230.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        viewModel.glucoseValue = DecimalFormat("#.#").format(it).toDouble()
                    }
                }
            }
        }
    }
}