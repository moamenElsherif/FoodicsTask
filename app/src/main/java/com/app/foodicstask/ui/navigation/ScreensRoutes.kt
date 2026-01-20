package com.app.foodicstask.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface ScreensRoutes: NavKey {
    @Serializable data object Menu: ScreensRoutes
    @Serializable data object Orders: ScreensRoutes
    @Serializable data object Settings: ScreensRoutes
}