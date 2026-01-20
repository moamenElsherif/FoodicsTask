package com.app.foodicstask.ui.screens.menu

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun MenuScreenHost(menuViewModel: MenuViewModel = hiltViewModel()) {
    Text("MenuScreenHost")
}