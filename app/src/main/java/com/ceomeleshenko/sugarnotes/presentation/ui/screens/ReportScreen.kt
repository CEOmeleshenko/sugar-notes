package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.FrameLayout
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap
import com.ceomeleshenko.sugarnotes.presentation.ui.components.NotesTable
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.ReportViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ReportScreen(
    viewModel: ReportViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val reportLayout = remember { FrameLayout(context) }

    val reportRecords = viewModel.getMonthlyRecords()

    val reportRecordsView = remember {
        ComposeView(context).apply {
            setContent {
                Column(modifier = Modifier.background(Color.White)) {
                    Text(
                        text = LocalDate.now().month.getDisplayName(
                            TextStyle.FULL_STANDALONE,
                            Locale.getDefault()
                        ).replaceFirstChar { it.titlecase() },
                        style = Typography.headlineSmall
                    )
                    Text(
                        text = "Самый низкий показатель: ${viewModel.lowestGlucoseValue.value} ммоль/л",
                        style = Typography.titleMedium
                    )
                    Text(
                        text = "Самый высокий показатель: ${viewModel.highestGlucoseValue.value} ммоль/л",
                        style = Typography.titleMedium
                    )
                    NotesTable(reportRecords = reportRecords)
                }
            }
        }
    }
    reportLayout.addView(reportRecordsView)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            AndroidView(factory = { reportLayout })
        }

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .weight(0.1f),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = {
                scope.launch {
                    val bitmap = withContext(Dispatchers.IO) {
                        reportRecordsView.drawToBitmap()
                    }
                    try {
                        saveImageToGallery(context, bitmap, "test.png")
                        Toast.makeText(
                            context,
                            "Отчет успешно сохранен в папку Pictures",
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (_: Exception) {
                        Toast.makeText(
                            context,
                            "Возникла ошибка при сохранении отчета: нет прав на запись",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }) {
                Text(text = "Сохранить")
            }
        }
    }
}

private suspend fun saveImageToGallery(context: Context, bitmap: Bitmap, fileName: String) =
    withContext(Dispatchers.IO) {
        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                put(MediaStore.MediaColumns.IS_PENDING, 1)
            }
        }

        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        if (uri != null) {
            resolver.openOutputStream(uri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                contentValues.clear()
                contentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                resolver.update(uri, contentValues, null, null)
            }
        }
    }
