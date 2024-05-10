package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.data.models.InsulinType
import com.ceomeleshenko.sugarnotes.presentation.ui.components.HorizontalNumberPicker
import com.ceomeleshenko.sugarnotes.presentation.ui.components.InsulinTypeSwitch
import com.ceomeleshenko.sugarnotes.presentation.ui.components.NavigationItem
import com.ceomeleshenko.sugarnotes.presentation.ui.components.VerticalNumberPicker
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel

private var glucose = 0.0
private var insulin = 0L
private var insulin_type = InsulinType.SHORT.toString()
private var food = 0L

@Composable
fun AddNoteScreen(
    navController: NavController,
    viewModel: NoteViewModel = koinViewModel()
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxSize()
    ) {
        GlucoseSection()
        InsulinSection()
        FoodSection()

        Button(onClick = {
            viewModel.insertNote(
                glucose = glucose,
                insulin = insulin,
                insulinType = insulin_type,
                food = food
            )
            navController.navigate(NavigationItem.Home.route)
        }
        ) {
            Text(text = stringResource(R.string.button_add_note))
        }
    }
}

@Composable
private fun GlucoseSection() {
    var glucoseValue by remember { mutableDoubleStateOf(5.0) }

    Column {
        Text(
            text = stringResource(R.string.add_note_section_glucose),
            style = Typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        glucoseValue.toFloat().toString() + " "
                                + stringResource(R.string.unit_glucose)
                    )
                }
                Row {
                    HorizontalNumberPicker(
                        maxNumber = 34.0,
                        selectedNumber = glucoseValue,
                        modifier = Modifier
                            .width(200.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        glucoseValue = DecimalFormat("#.#").format(it).toDouble()
                        glucose = glucoseValue
                    }
                }

            }
        }
    }
}

@Composable
private fun InsulinSection() {
    var insulinValue by remember { mutableLongStateOf(0L) }
    var insulinType by remember { mutableStateOf(InsulinType.SHORT) }

    Column {
        Text(
            text = stringResource(R.string.add_note_section_insulin),
            style = Typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
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
                    modifier = Modifier
                        .weight(2f)
                ) {
                    Text(
                        insulinValue.toString() + " "
                                + stringResource(R.string.unit_insulin)
                    )
                    InsulinTypeSwitch(insulinType) {
                        insulinType = it
                        insulin_type = insulinType.toString()
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    VerticalNumberPicker(
                        maxNumber = 50L,
                        selectedNumber = insulinValue,
                        modifier = Modifier
                            .height(140.dp)
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        insulinValue = it.toLong()
                        insulin = insulinValue
                    }
                }
            }
        }
    }
}

@Composable
private fun FoodSection() {
    var foodValue by remember { mutableLongStateOf(0L) }

    Column {
        Text(
            text = stringResource(R.string.add_note_section_food),
            style = Typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
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
                        if (foodValue > 0) foodValue -= 1L
                        food = foodValue
                    }) {
                        Icon(Icons.Rounded.KeyboardArrowDown, contentDescription = null)
                    }
                    Text(foodValue.toString())
                    IconButton(onClick = {
                        foodValue += 1L
                        food = foodValue
                    }) {
                        Icon(Icons.Rounded.KeyboardArrowUp, contentDescription = null)
                    }
                }
                Text(text = stringResource(R.string.unit_food))
            }
        }
    }
}

@Composable
private fun DatetimeSection() {

}