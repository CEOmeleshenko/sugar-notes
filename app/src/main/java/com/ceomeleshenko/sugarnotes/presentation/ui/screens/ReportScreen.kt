package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.drawToBitmap
import com.ceomeleshenko.sugarnotes.presentation.ui.components.NotesTable
import com.ceomeleshenko.sugarnotes.presentation.ui.components.Record
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.ReportViewModel
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisLabelComponent
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.fullWidth
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineSpec
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.compose.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.common.of
import com.patrykandpatrick.vico.compose.common.shape.dashed
import com.patrykandpatrick.vico.core.cartesian.HorizontalLayout
import com.patrykandpatrick.vico.core.cartesian.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.cartesian.data.AxisValueOverrider
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.common.Dimensions
import com.patrykandpatrick.vico.core.common.shape.Shape
import dev.shreyaspatil.capturable.capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeApi::class)
@Composable
fun ReportScreen(
    viewModel: ReportViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val captureController = rememberCaptureController()

    val scrollState = rememberScrollState()


    val records = listOf(
        Record("20 июня", "08:00", 3.5f, 2, 5),
        Record("20 июня", "12:00", 8.0f, 8, 6),
        Record("20 июня", "18:00", 14.0f, 12, 7),
        Record("21 июня", "08:00", 5.0f, 9, 5),
        Record("21 июня", "12:00", 4.5f, 7, 6),
        Record("21 июня", "18:00", 11.0f, 11, 7),
        Record("22 июня", "08:00", 5.0f, 9, 5),
        Record("22 июня", "12:00", 4.5f, 7, 6),
        Record("22 июня", "18:00", 11.0f, 11, 7),
        Record("23 июня", "08:00", 5.0f, 9, 5),
        Record("23 июня", "12:00", 4.5f, 7, 6),
        Record("23 июня", "18:00", 11.0f, 11, 7),
        Record("24 июня", "08:00", 5.0f, 9, 5),
        Record("24 июня", "12:00", 4.5f, 7, 6),
        Record("24 июня", "18:00", 11.0f, 11, 7),
        Record("25 июня", "08:00", 5.0f, 9, 5),
        Record("25 июня", "12:00", 4.5f, 7, 6),
        Record("25 июня", "18:00", 11.0f, 11, 7),

        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .capturable(captureController)
                .background(Color.White)
                .wrapContentHeight()
                .fillMaxWidth()
//                .weight(1f)
        ) {
            Text(text = "Месяц", style = Typography.headlineSmall)
            Text(text = "Самый низкий показатель: 3.2 ммоль/л", style = Typography.titleMedium)
            Text(text = "Самый высокий показатель: 18.5 ммоль/л", style = Typography.titleMedium)
            NotesTable(records = records)
        }
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(onClick = {
                scope.launch {
                    val bitmapAsync = captureController.captureAsync()
                    try {
                        val bitmap = withContext(Dispatchers.Default) {
                            bitmapAsync.await().asAndroidBitmap()
                        }
                        saveImageToGallery(context, bitmap, "test.png")
                    } catch (error: Throwable) {
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Text(text = "Сохранить")
            }
        }
    }
}

private suspend fun captureTableToBitmap(context: Context, records: List<Record>): Bitmap {
    return withContext(Dispatchers.Default) {
        val composeView = ComposeView(context).apply {
            setContent {
                MaterialTheme {
                    Column(modifier = Modifier.background(Color.White)) {
                        NotesTable(records = records)
                    }
                }
            }
        }

        val measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        composeView.measure(measureSpec, measureSpec)
        composeView.layout(0, 0, composeView.measuredWidth, composeView.measuredHeight)

        composeView.drawToBitmap()
    }
}

@Composable
private fun Chart(
    viewModel: ReportViewModel
) {
    val values by viewModel.chartValues.collectAsState()
    val startAxisValues by viewModel.chartAxis.collectAsState()

    val modelProducer = remember { CartesianChartModelProducer.build() }
    LaunchedEffect(values) {
        modelProducer.tryRunTransaction {
            lineSeries { series(values) }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CartesianChartHost(
                rememberCartesianChart(
                    rememberLineCartesianLayer(
                        axisValueOverrider = AxisValueOverrider.fixed(
                            minY = 0f,
                            maxY = values.max() + 4f
                        ),
                        lines = listOf(rememberLineSpec(dataLabel = rememberTextComponent()))
                    ),
                    startAxis = rememberStartAxis(
                        label =
                        rememberAxisLabelComponent(
                            color = MaterialTheme.colorScheme.onBackground,
                            background = rememberShapeComponent(
                                shape = Shape.Pill,
                                color = Color.Transparent,
                                strokeColor = MaterialTheme.colorScheme.outlineVariant,
                                strokeWidth = 1.dp,
                            ),
                            padding = Dimensions.of(horizontal = 6.dp, vertical = 2.dp),
                            margins = Dimensions.of(end = 8.dp),
                        ),
                        axis = null,
                        tick = null,
                        itemPlacer = remember { AxisItemPlacer.Vertical.count(count = { 5 }) },
                        valueFormatter = { value, _, _ -> value.toInt().toString() },
                        guideline = rememberLineComponent(
                            color = MaterialTheme.colorScheme.outlineVariant,
                            shape =
                            remember {
                                Shape.dashed(
                                    shape = Shape.Pill,
                                    dashLength = 4.dp,
                                    gapLength = 8.dp,
                                )
                            },
                        ),
                    ),
                    bottomAxis = rememberBottomAxis(
                        guideline = null,
                        itemPlacer =
                        remember {
                            AxisItemPlacer.Horizontal.default(
                                addExtremeLabelPadding = true,
                            )
                        },
                        valueFormatter = { x, _, _ -> startAxisValues[x.toInt() % startAxisValues.size] }
                    )
                ),
                modelProducer = modelProducer,
                horizontalLayout = HorizontalLayout.fullWidth(),
                modifier = Modifier.padding(8.dp)
            )
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

data class ReportData(
    val highestSugarLevel: Float = 0f,
    val lowestSugarLevel: Float = 0f,
    val dailyRecords: List<DailyRecord> = emptyList()
)

data class DailyRecord(
    val date: String,
    val sugarLevel: Float,
    val insulinAmount: Float,
    val insulinType: String,
    val foodAmount: Float
)