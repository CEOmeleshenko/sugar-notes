package com.ceomeleshenko.sugarnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ceomeleshenko.sugarnotes.presentation.ui.components.BottomNavigationBar
import com.ceomeleshenko.sugarnotes.presentation.ui.screens.AddNoteScreen
import com.ceomeleshenko.sugarnotes.presentation.ui.screens.HomeScreen
import com.ceomeleshenko.sugarnotes.presentation.ui.screens.StatisticScreen
import com.ceomeleshenko.sugarnotes.presentation.ui.theme.SugarNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            SugarNotesTheme {
                Scaffold(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "Home",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("Home") { HomeScreen() }
                        composable("Statistic") { StatisticScreen() }
                        composable("AddNote") { AddNoteScreen() }
                    }
                }
            }
        }
    }
}