package com.app.foodicstask.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.app.foodicstask.ui.screens.menu.MenuScreenHost
import com.app.foodicstask.ui.screens.orders.OrdersScreenHost
import com.app.foodicstask.ui.screens.settings.SettingsScreenHost

@Composable
fun AppNavGraph(modifier: Modifier = Modifier, navigationViewModel: NavigationViewModel = hiltViewModel()) {
    NavDisplay(
        backStack = navigationViewModel.backStack,
        modifier = modifier,
        transitionSpec = {
            fadeIn(tween(300)) togetherWith fadeOut(tween(300))
        },
        entryProvider = entryProvider {
            entry<ScreensRoutes.Menu> {
                MenuScreenHost()
            }
            entry<ScreensRoutes.Orders> {
                OrdersScreenHost()
            }

            entry<ScreensRoutes.Settings>{
                SettingsScreenHost()
            }
        }
    )
}

