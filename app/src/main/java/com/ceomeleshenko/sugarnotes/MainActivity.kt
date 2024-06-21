package com.ceomeleshenko.sugarnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ceomeleshenko.sugarnotes.presentation.ui.components.BottomNavigationBar
import com.ceomeleshenko.sugarnotes.presentation.ui.screens.AddNoteScreen
import com.ceomeleshenko.sugarnotes.presentation.ui.screens.HomeScreen
import com.ceomeleshenko.sugarnotes.presentation.ui.screens.ReportScreen
import com.ceomeleshenko.sugarnotes.presentation.ui.screens.OnboardingScreen
import com.ceomeleshenko.sugarnotes.presentation.ui.screens.StatisticScreen
import com.ceomeleshenko.sugarnotes.presentation.ui.screens.WelcomeScreen
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.SugarNotesTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinContext {
                SugarNotesTheme {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    val tutorial = remember { mutableStateOf(true) }

                    Scaffold(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxSize(),
                        bottomBar = {
                            if (currentRoute != "Welcome" && currentRoute != "Onboarding") {
                                BottomNavigationBar(navController = navController)
                            }
                        }
                    ) { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = "Welcome",
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            composable("Welcome") { WelcomeScreen(tutorial.value, navController) }
                            composable("Onboarding") { OnboardingScreen(navController) }
                            composable("Home") { HomeScreen() }
                            composable("Statistic") { StatisticScreen(navController) }
                            composable("AddNote") { AddNoteScreen(navController) }
                            composable("Report") { ReportScreen() }
                        }
                    }
                }
            }
        }
    }
}