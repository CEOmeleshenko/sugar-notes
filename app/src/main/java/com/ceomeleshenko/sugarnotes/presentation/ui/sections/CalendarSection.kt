package com.ceomeleshenko.sugarnotes.presentation.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Preview(showSystemUi = true)
@Composable
fun CalendarSection(
    viewModel: NoteViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(viewModel.dateValue, formatter)

    val month = date.month.toString()
    val day = date.dayOfMonth.toString()
    val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())

    Box(
        modifier = modifier
            .padding(16.dp)
            .wrapContentHeight()
            .width(180.dp)
//            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .clickable { viewModel.showDatePickerDialog = true },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = month,
                style = Typography.titleLarge,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                text = day,
                style = Typography.displayMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                text = dayOfWeek,
                style = Typography.titleSmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}