package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import android.icu.text.DecimalFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.data.models.InsulinType
import com.ceomeleshenko.sugarnotes.presentation.ui.components.DateSelectionDialog
import com.ceomeleshenko.sugarnotes.presentation.ui.components.HorizontalNumberPicker
import com.ceomeleshenko.sugarnotes.presentation.ui.components.InsulinTypeSwitch
import com.ceomeleshenko.sugarnotes.presentation.ui.components.NavigationItem
import com.ceomeleshenko.sugarnotes.presentation.ui.components.TimeSelectionDialog
import com.ceomeleshenko.sugarnotes.presentation.ui.components.VerticalNumberPicker
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.DatetimeSection
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.FoodSection
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.GlucoseSection
import com.ceomeleshenko.sugarnotes.presentation.ui.sections.InsulinSection
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.AddNoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddNoteScreen(
    navController: NavController,
    viewModel: AddNoteViewModel = koinViewModel()
) {

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
                    viewModel.insertNote()
                    navController.navigate(NavigationItem.Home.route)
                }, modifier = Modifier.width(200.dp)
            ) {
                Text(text = stringResource(R.string.button_add_note))
            }
        }
    }
}