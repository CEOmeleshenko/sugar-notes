package com.ceomeleshenko.sugarnotes.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ceomeleshenko.sugarnotes.R

private val items = listOf(
    NavigationItem.Home,
    NavigationItem.Statistic
)

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(modifier = Modifier.fillMaxWidth()) {
        NavigationBar {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 24.dp, 0.dp)
            ) {
                items.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = stringResource(item.title)
                            )
                        },
                        label = { Text(stringResource(id = item.title)) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = false
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
        if (currentRoute == NavigationItem.Home.route) {
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-16).dp, y = (-30).dp),
                shape = RoundedCornerShape(40.dp),
                onClick = { navController.navigate(NavigationItem.AddNote.route) }
            ) {
                Icon(
                    imageVector = NavigationItem.AddNote.icon,
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

sealed class NavigationItem(
    val route: String,
    val icon: ImageVector,
    val title: Int
) {
    data object Home :
        NavigationItem("Home", Icons.Rounded.Star, R.string.navbar_item_home)

    data object Statistic :
        NavigationItem("Statistic", Icons.Rounded.Info, R.string.navbar_item_statistic)

    data object AddNote :
        NavigationItem("AddNote", Icons.Rounded.Add, R.string.navbar_item_add)
}
