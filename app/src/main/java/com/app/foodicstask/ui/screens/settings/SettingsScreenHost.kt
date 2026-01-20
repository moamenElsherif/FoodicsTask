package com.app.foodicstask.ui.screens.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreenHost(viewModel: SettingsViewModel = hiltViewModel()) {
    Text("SettingsScreenHost")
}
