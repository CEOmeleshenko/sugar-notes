package com.ceomeleshenko.sugarnotes.presentation.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.text.isDigitsOnly
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Date

@Composable
fun TimeSelectionDialog(
    onTimeSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    var selectedHour by remember { mutableStateOf(LocalTime.now().hour.toString()) }
    var selectedMinute by remember { mutableStateOf(LocalTime.now().minute.toString()) }

    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextField(
                        value = selectedHour,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            if (
                                it.isEmpty() ||
                                (it.toIntOrNull() ?: 0) in 0..23 &&
                                it.isDigitsOnly() &&
                                it.length <= 2
                            ) {
                                selectedHour = it
                            }
                        },
                        modifier = Modifier.width(60.dp),
                        textStyle = MaterialTheme.typography.titleMedium,
                    )

                    Text(text = ":", fontWeight = FontWeight.Bold, style = Typography.displayMedium)

                    TextField(
                        value = selectedMinute,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            if (
                                it.isEmpty() ||
                                (it.toIntOrNull() ?: 0) in 0..59 &&
                                it.isDigitsOnly() &&
                                it.length <= 2
                            ) {
                                selectedMinute = it
                            }
                        },
                        modifier = Modifier.width(60.dp),
                        textStyle = MaterialTheme.typography.titleMedium
                    )

                }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismissRequest,
                    ) {
                        Text(stringResource(R.string.button_cancel))
                    }
                    Button(
                        onClick = {
                            if (selectedHour.length <= 1) selectedHour = "0$selectedHour"
                            if (selectedMinute.length <= 1) selectedMinute = "0$selectedMinute"

                            onTimeSelected("$selectedHour:$selectedMinute")
                            onDismissRequest()
                        }
                    ) {
                        Text(stringResource(R.string.button_select))
                    }
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelectionDialog(
    onDateSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val state = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        initialDisplayMode = DisplayMode.Picker
    )

    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(
                onClick = {
                    onDateSelected(
                        SimpleDateFormat("yyyy-MM-dd")
                            .format(Date(state.selectedDateMillis!!))
                    )
                    onDismissRequest()
                }
            ) {
                Text(stringResource(R.string.button_select))
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = onDismissRequest,
                modifier = Modifier
            ) {
                Text(stringResource(R.string.button_cancel))
            }
        }
    ) {
        DatePicker(state = state)
    }
}