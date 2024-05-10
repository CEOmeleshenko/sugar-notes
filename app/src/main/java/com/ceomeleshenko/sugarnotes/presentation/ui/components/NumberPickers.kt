package com.ceomeleshenko.sugarnotes.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun HorizontalNumberPicker(
    maxNumber: Double,
    selectedNumber: Double,
    modifier: Modifier = Modifier,
    onNumberSelected: (Number) -> Unit
) {
    val lazyListState = rememberLazyListState()

    val numbers = generateSequence(0.0) { it + 0.1 }
        .takeWhile { it <= maxNumber + 0.1 }
        .toList()
        .map { it.toFloat() }

    val selectedIndex = numbers.indexOf(selectedNumber.toFloat())

    LazyRow(
        state = lazyListState,
        modifier = modifier
    ) {
        items(numbers) { number ->
            NumberItem(
                number = number,
                isSelected = number == selectedNumber.toFloat(),
                onNumberSelected = onNumberSelected
            )
        }
    }

    LaunchedEffect(selectedIndex) {
        lazyListState.animateScrollToItem(if (selectedIndex - 2 >= 0) selectedIndex - 2 else 0)
    }
}

@Composable
fun VerticalNumberPicker(
    maxNumber: Long,
    selectedNumber: Long,
    modifier: Modifier = Modifier,
    onNumberSelected: (Number) -> Unit
) {
    val lazyListState = rememberLazyListState()

    val numbers = generateSequence(0L) { it + 1 }
        .takeWhile { it <= maxNumber + 0.1 }
        .toList()

    val selectedIndex = numbers.indexOf(selectedNumber)

    LazyColumn(
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        items(numbers) { number ->
            NumberItem(
                number = number,
                isSelected = number == selectedNumber,
                onNumberSelected = onNumberSelected
            )
        }
    }

    LaunchedEffect(selectedIndex) {
        lazyListState.animateScrollToItem(if (selectedIndex - 2 >= 0) selectedIndex - 2 else 0)
    }
}

@Composable
private fun NumberItem(
    number: Number,
    isSelected: Boolean,
    onNumberSelected: (Number) -> Unit,
    modifier: Modifier = Modifier
) {
    val textColor = if (isSelected) Color.Red else Color.Black
    val text = if (number is Double) number.toFloat().toString() else number.toString()
    Text(
        color = textColor,
        text = text,
        modifier = modifier
            .clickable { onNumberSelected(number) }
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .wrapContentSize(Alignment.Center)
    )
}