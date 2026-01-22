package com.app.foodicstask.ui.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class NavigationViewModel: ViewModel() {
    val backStack = mutableStateListOf<ScreensRoutes>(ScreensRoutes.Menu)
}