package com.ceomeleshenko.sugarnotes.presentation.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.ceomeleshenko.sugarnotes.presentation.ui.components.InsulinTypeSwitch
import com.ceomeleshenko.sugarnotes.presentation.ui.components.VerticalNumberPicker
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.AddNoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun InsulinSection(viewModel: AddNoteViewModel = koinViewModel()) {
    Column {
        Text(
            text = stringResource(R.string.add_note_section_insulin),
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(2f)
                ) {
                    Text(
                        text = viewModel.insulinValue.toString() + " " + stringResource(R.string.unit_insulin),
                        fontWeight = FontWeight.Medium,
                        style = Typography.titleLarge
                    )
                    InsulinTypeSwitch(viewModel.insulinType) {
                        viewModel.insulinType = it
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    VerticalNumberPicker(
                        maxNumber = 50L,
                        selectedNumber = viewModel.insulinValue,
                        modifier = Modifier
                            .height(140.dp)
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        viewModel.insulinValue = it.toLong()
                    }
                }
            }
        }
    }
}