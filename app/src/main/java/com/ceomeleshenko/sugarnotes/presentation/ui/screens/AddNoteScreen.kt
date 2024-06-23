package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.data.UpdateCheckWorker
import com.ceomeleshenko.sugarnotes.presentation.ui.components.DateSelectionDialog
import com.ceomeleshenko.sugarnotes.presentation.ui.components.NavigationItem
import com.ceomeleshenko.sugarnotes.presentation.ui.components.TimeSelectionDialog
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.DatetimeSection
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.FoodSection
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.GlucoseSection
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.InsulinSection
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.AddNoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddNoteScreen(
    navController: NavController,
    viewModel: AddNoteViewModel = koinViewModel()
) {

    val context = LocalContext.current

    if (viewModel.showTimePickerDialog) {
        TimeSelectionDialog(
            onTimeSelected = { viewModel.timeValue = it },
            onDismissRequest = { viewModel.showTimePickerDialog = false }
        )
    }

    if (viewModel.showDatePickerDialog) {
        DateSelectionDialog(
            onDateSelected = { viewModel.dateValue = it },
            onDismissRequest = { viewModel.showDatePickerDialog = false }
        )
    }

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.navigate(NavigationItem.Home.route) }) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }
            DatetimeSection()
        }

        GlucoseSection()
        InsulinSection()
        FoodSection()

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                onClick = {
                    try {
                        viewModel.insertNote()
                        navController.navigate(NavigationItem.Home.route)
                        Toast.makeText(context, "Запись успешно добавлена", Toast.LENGTH_SHORT).show()
                        UpdateCheckWorker.lastUpdateTime = System.currentTimeMillis()
                    }
                    catch (_: Exception){
                        Toast.makeText(context, "Ошибка при добавлении записи", Toast.LENGTH_SHORT).show()
                    }
                }, modifier = Modifier.width(200.dp)
            ) {
                Text(text = stringResource(R.string.button_add_note))
            }
        }
    }
}