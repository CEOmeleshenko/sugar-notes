package com.ceomeleshenko.sugarnotes.presentation.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ceomeleshenko.sugarnotes.presentation.ui.components.ChartTypeSwitch
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.StatisticViewModel
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
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChartSection(
    viewModel: StatisticViewModel = koinViewModel(),
    modifier: Modifier = Modifier
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
        modifier = modifier
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

            ChartTypeSwitch(selectedType = viewModel.chartType) {
                viewModel.changeChartType(it)
            }
        }
    }
}
