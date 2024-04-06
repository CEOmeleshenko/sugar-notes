package com.ceomeleshenko.sugarnotes.ui

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ceomeleshenko.sugarnotes.data.entity.Note
import com.ceomeleshenko.sugarnotes.ui.note.NoteViewModel
import com.ceomeleshenko.sugarnotes.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.ui.theme.GreenPrimary

private var glucose = 0
private var bread = 0
private var insulin = 0

@Composable
fun AddNoteScreen(
    viewModel: NoteViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Close",
                tint = Color.White,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable {
                        navController.navigate("Home")
                    }
            )
        }

        Text(
            text = "Новая запись",
            style = Typography.titleLarge,
            modifier = Modifier
                .padding(16.dp, 0.dp)
        )

        val filterDigits = { input: String ->
            input.filter { it.isDigit() }
        }

        Row(
            modifier = Modifier.padding(16.dp),
            Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(170.dp)
                    .height(156.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {
                Text(
                    text = "Уровень глюкозы",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopStart),
                    style = Typography.bodyLarge
                )
                val (glucoseValue, setText) = remember { mutableStateOf("") }
                TextField(
                    value = glucoseValue,
                    onValueChange = { newText ->
                        setText(newText)
                        if (newText.isDigitsOnly()) glucose = newText.toInt()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .width(80.dp)
                        .padding(8.dp),
                    textStyle = Typography.bodyLarge,
                    singleLine = true
                )
            }

            Box(
                modifier = Modifier
                    .width(148.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {
                Text(
                    text = "Время",
                    modifier = Modifier.padding(8.dp),
                    style = Typography.bodyLarge
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(90.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {
                Text(
                    text = "Потребление",
                    modifier = Modifier.padding(8.dp),
                    style = Typography.bodyLarge
                )
                val (breadValue, setText) = remember { mutableStateOf("") }
                TextField(
                    value = breadValue,
                    onValueChange = { newText ->
                        setText(newText)
                        if (breadValue.isDigitsOnly()) bread = newText.toInt()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .width(80.dp)
                        .padding(8.dp),
                    textStyle = Typography.bodyLarge,
                    singleLine = true
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Box(
                modifier = Modifier
                    .width(334.dp)
                    .height(168.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {
                Text(
                    text = "Инсулин",
                    modifier = Modifier.padding(8.dp),
                    style = Typography.bodyLarge
                )
                val (insulinValue, setText) = remember { mutableStateOf("") }
                TextField(
                    value = insulinValue,
                    onValueChange = { newText ->
                        setText(newText)
                        if (insulinValue.isDigitsOnly()) insulin = newText.toInt()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .width(80.dp)
                        .padding(8.dp),
                    textStyle = Typography.bodyLarge,
                    singleLine = true
                )
            }
        }

        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            onClick = {
                val text = "$glucose $bread $insulin"
                Toast.makeText(navController.context, "Запись добавлена", Toast.LENGTH_SHORT).show()
                viewModel.insertNote(Note(glucose = glucose, bread = bread, insulin = insulin))
                navController.navigate("Home")
            }
        ) {
            Text(text = "Добавить запись")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddNoteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Close",
                tint = Color.White,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable { }
            )
        }

        Text(
            text = "Новая запись",
            style = Typography.titleLarge,
            modifier = Modifier
                .padding(16.dp, 0.dp)
        )

        val filterDigits = { input: String ->
            input.filter { it.isDigit() }
        }

        Row(
            modifier = Modifier.padding(16.dp),
            Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(170.dp)
                    .height(156.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {
                Text(
                    text = "Уровень глюкозы",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopStart),
                    style = Typography.bodyLarge
                )
                val (glucoseValue, setText) = remember { mutableStateOf("") }
                TextField(
                    value = glucoseValue,
                    onValueChange = { newText ->
                        setText(filterDigits(newText))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .width(80.dp)
                        .padding(8.dp),
                    textStyle = Typography.bodyLarge,
                    singleLine = true
                )
            }

            Box(
                modifier = Modifier
                    .width(148.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {
                Text(
                    text = "Время",
                    modifier = Modifier.padding(8.dp),
                    style = Typography.bodyLarge
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(90.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {
                Text(
                    text = "Потребление",
                    modifier = Modifier.padding(8.dp),
                    style = Typography.bodyLarge
                )
                val (breadValue, setText) = remember { mutableStateOf("") }
                TextField(
                    value = breadValue,
                    onValueChange = { newText ->
                        setText(filterDigits(newText))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .width(80.dp)
                        .padding(8.dp),
                    textStyle = Typography.bodyLarge,
                    singleLine = true
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Box(
                modifier = Modifier
                    .width(334.dp)
                    .height(168.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {
                Text(
                    text = "Инсулин",
                    modifier = Modifier.padding(8.dp),
                    style = Typography.bodyLarge
                )
                val (insulinValue, setText) = remember { mutableStateOf("") }
                TextField(
                    value = insulinValue,
                    onValueChange = { newText ->
                        setText(filterDigits(newText))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .width(80.dp)
                        .padding(8.dp),
                    textStyle = Typography.bodyLarge,
                    singleLine = true
                )
            }
        }

        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Добавить запись")
        }
    }
}