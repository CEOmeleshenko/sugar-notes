package com.ceomeleshenko.sugarnotes.presentation.ui.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.NotesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AverageValuesSection(
    viewModel: NotesViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            AverageValue(
                value = viewModel.averageGlucose.toString(),
                unit = stringResource(R.string.unit_glucose),
                icon = painterResource(id = R.drawable.glucose)
            )
            AverageValue(
                value = viewModel.averageFood.toString(),
                unit = stringResource(R.string.unit_food),
                icon = painterResource(id = R.drawable.food)
            )
            AverageValue(
                value = viewModel.averageInsulin.toString(),
                unit = stringResource(R.string.unit_insulin),
                icon = painterResource(id = R.drawable.insulin)
            )
        }
    }
}

@Composable
private fun AverageValue(
    value: String,
    unit: String,
    icon: Painter
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        Image(
            painter = icon,
            contentDescription = "",
            modifier = Modifier.size(20.dp).padding(bottom = 4.dp)
        )
        Text(
            text = value,
            style = Typography.headlineMedium
        )
        Text(text = unit)
    }
}