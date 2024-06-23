package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController
) {
    val pagerState = rememberPagerState(0, 0f) { 3 }

    var imageResource = remember { 0 }
    var title = remember { "" }
    var text = remember { "" }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.9f)
        ) {
            HorizontalPager(state = pagerState) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (pagerState.currentPage) {
                        0 -> {
                            imageResource = R.drawable.onboarding_report
                            title = stringResource(R.string.onboarding_title_1)
                            text = stringResource(R.string.onboarding_text_1)
                        }

                        1 -> {
                            imageResource = R.drawable.onboarding_monitor
                            title = stringResource(R.string.onboarding_title_2)
                            text = stringResource(R.string.onboarding_text_2)
                        }

                        2 -> {
                            imageResource = R.drawable.onboarding_healthcare
                            title = stringResource(R.string.onboarding_title_3)
                            text = stringResource(R.string.onboarding_text_3)
                        }
                    }
                    Image(
                        painter = painterResource(imageResource),
                        contentDescription = "",
                        modifier = Modifier
                            .size(160.dp)
                            .animateContentSize()
                    )
                    Text(
                        text = title,
                        style = Typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = text,
                        style = Typography.titleMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .animateContentSize()
                    )
                }
            }
        }
        if (pagerState.currentPage == pagerState.pageCount - 1) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.05f),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onClick = {
                        navController.navigate("Home") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    }) {
                    Text(text = stringResource(R.string.button_finish))
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.05f)
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(3) {
                PagerIndicator(isSelected = pagerState.currentPage == it)
            }
        }
    }
}

@Composable
private fun PagerIndicator(
    isSelected: Boolean
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray,
                shape = CircleShape
            )
            .height(8.dp)
            .width(40.dp)
    )
}