package com.ceomeleshenko.sugarnotes.presentation.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.AddNoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FoodSection(viewModel: AddNoteViewModel = koinViewModel()) {
    Column {
        Text(
            text = stringResource(R.string.add_note_section_food),
            style = Typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ), modifier = Modifier
                .width(300.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = {
                        if (viewModel.foodValue > 0) viewModel.foodValue -= 1L
                    }) {
                        Icon(Icons.Rounded.KeyboardArrowDown, contentDescription = null)
                    }
                    Text(
                        text = viewModel.foodValue.toString(),
                        fontWeight = FontWeight.Medium,
                        style = Typography.titleLarge
                    )
                    IconButton(onClick = {
                        viewModel.foodValue += 1L
                    }) {
                        Icon(Icons.Rounded.KeyboardArrowUp, contentDescription = null)
                    }
                }
                Text(text = stringResource(R.string.unit_food))
            }
        }
    }
}