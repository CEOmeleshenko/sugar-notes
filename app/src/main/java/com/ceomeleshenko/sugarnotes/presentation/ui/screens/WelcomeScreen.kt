package com.ceomeleshenko.sugarnotes.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ceomeleshenko.sugarnotes.R
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.Typography
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(
    showOnboarding: Boolean,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        delay(1400)
        if (showOnboarding) {
            navController.navigate("Onboarding") {
                popUpTo("Welcome") { inclusive = false }
            }
        } else {
            navController.navigate("Home") {
                popUpTo("Welcome") { inclusive = true }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "App Icon",
                modifier = Modifier
                    .size(128.dp)
            )
            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                style = Typography.headlineLarge,
            )
        }
    }
}