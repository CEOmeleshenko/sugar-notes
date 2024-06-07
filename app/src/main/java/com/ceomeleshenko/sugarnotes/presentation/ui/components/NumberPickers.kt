package com.ceomeleshenko.sugarnotes.presentation.ui.components

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import java.math.RoundingMode

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
        .map { it.toBigDecimal().setScale(1, RoundingMode.DOWN).toDouble() }
        .distinct()

    Log.d("TAG", "HorizontalNumberPicker: $numbers")

    val selectedIndex = numbers.indexOf(selectedNumber)

    LazyRow(
        state = lazyListState,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier.height(50.dp)
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
        lazyListState.animateScrollToItem(if (selectedIndex - 3 >= 0) selectedIndex - 3 else 0)
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
    val textColor =
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
    val style = if (isSelected) Typography.titleMedium else Typography.titleSmall
//    val text = if (number is Double) number.toFloat().toString() else number.toString()
    val text = number.toString()
    Text(
        color = textColor,
        fontWeight = fontWeight,
        style = style,
        text = text,
        modifier = modifier
            .clickable { onNumberSelected(number) }
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .wrapContentSize(Alignment.Center)
            .animateContentSize()
    )
}