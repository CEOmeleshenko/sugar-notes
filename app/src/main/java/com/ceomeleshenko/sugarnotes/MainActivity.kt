package com.ceomeleshenko.sugarnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ceomeleshenko.sugarnotes.presentation.ui.screen.HomeScreen
import com.ceomeleshenko.sugarnotes.presentation.ui.screen.StatisticScreen
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.SugarNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SugarNotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "Home") {
                        composable("Home") { HomeScreen() }
                        composable("Statistic") { StatisticScreen() }
                    }
                }
            }
        }
    }
}