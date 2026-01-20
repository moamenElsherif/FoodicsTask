package com.app.foodicstask.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNavigationBar(navigationViewModel: NavigationViewModel) {
    val items = listOf(
        BottomNavItem.Menu,
        BottomNavItem.Orders,
        BottomNavItem.Settings
    )
    NavigationBar {
        items.forEach {
            NavigationBarItem(
                selected = navigationViewModel.backStack.last() == it.route,
                onClick = { 
                    navigationViewModel.backStack.clear()
                    navigationViewModel.backStack.add(it.route) 
                },
                icon = { Icon(it.icon, contentDescription = null) },
                label = { Text(it.title) }
            )
        }
    }
}

sealed class BottomNavItem(val route: ScreensRoutes, val icon: ImageVector, val title: String) {
    object Menu : BottomNavItem(ScreensRoutes.Menu, Icons.Default.Menu, "Menu")
    object Orders : BottomNavItem(ScreensRoutes.Orders, Icons.Default.DateRange, "Orders")
    object Settings : BottomNavItem(ScreensRoutes.Settings, Icons.Default.Settings, "Settings")
}