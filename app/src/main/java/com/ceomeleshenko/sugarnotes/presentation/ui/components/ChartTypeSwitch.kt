package com.ceomeleshenko.sugarnotes.presentation.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.data.models.ChartType
import com.ceomeleshenko.sugarnotes.data.models.InsulinType
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography

@Composable
fun ChartTypeSwitch(
    selectedType: ChartType,
    modifier: Modifier = Modifier,
    onChartTypeSwitch: (ChartType) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )
    ) {

        SwitchItem(
            chartType = ChartType.DAILY,
            isSelected = selectedType == ChartType.DAILY,
            onChartTypeSwitch = onChartTypeSwitch
        )
        SwitchItem(
            chartType = ChartType.WEEKLY,
            isSelected = selectedType == ChartType.WEEKLY,
            onChartTypeSwitch = onChartTypeSwitch
        )
        SwitchItem(
            chartType = ChartType.MONTHLY,
            isSelected = selectedType == ChartType.MONTHLY,
            onChartTypeSwitch = onChartTypeSwitch
        )
    }
}

@Composable
private fun SwitchItem(
    chartType: ChartType,
    isSelected: Boolean,
    onChartTypeSwitch: (ChartType) -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp)
        }
    )

    Box(modifier = Modifier
        .wrapContentWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(backgroundColor)
        .padding(horizontal = 8.dp, vertical = 4.dp)
        .clickable {
            onChartTypeSwitch(chartType)
        }) {
        Text(
            text = when (chartType) {
                ChartType.DAILY -> "день"
                ChartType.WEEKLY -> "неделя"
                ChartType.MONTHLY -> "месяц"
            },
            style = Typography.bodyLarge,
            modifier = modifier
                .background(backgroundColor)
                .align(Alignment.Center)
        )
    }
}