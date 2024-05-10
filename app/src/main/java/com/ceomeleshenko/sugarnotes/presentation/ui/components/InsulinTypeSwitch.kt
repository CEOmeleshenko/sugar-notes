package com.ceomeleshenko.sugarnotes.presentation.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.data.models.InsulinType
import androidx.compose.material.*
import androidx.compose.material3.Typography
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.ui.platform.LocalContext
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography

@Composable
fun InsulinTypeSwitch(
    selectedType: InsulinType,
    modifier: Modifier = Modifier,
    onInsulinTypeSwitch: (InsulinType) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .width(180.dp)
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
            insulinType = InsulinType.SHORT,
            isSelected = selectedType == InsulinType.SHORT,
            onInsulinTypeSwitch = onInsulinTypeSwitch
        )
        SwitchItem(
            insulinType = InsulinType.LONG,
            isSelected = selectedType == InsulinType.LONG,
            onInsulinTypeSwitch = onInsulinTypeSwitch
        )
    }
}

@Composable
private fun SwitchItem(
    insulinType: InsulinType,
    isSelected: Boolean,
    onInsulinTypeSwitch: (InsulinType) -> Unit,
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
        .width(90.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(backgroundColor)
        .padding(vertical = 4.dp)
        .clickable {
            onInsulinTypeSwitch(insulinType)
        }) {
        Text(
            text = when (insulinType) {
                InsulinType.SHORT -> stringResource(R.string.insulin_type_short)
                InsulinType.LONG -> stringResource(R.string.insulin_type_long)
            },
            style = Typography.bodyLarge,
            modifier = modifier
                .background(backgroundColor)
                .align(Alignment.Center)
        )
    }
}