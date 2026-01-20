package com.app.foodicstask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.foodicstask.ui.navigation.AppNavGraph
import com.app.foodicstask.ui.navigation.BottomNavigationBar
import com.app.foodicstask.ui.navigation.NavigationViewModel
import com.app.foodicstask.ui.theme.FoodicsTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodicsTaskTheme {
                val navigationViewModel: NavigationViewModel = hiltViewModel()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navigationViewModel)
                    }
                ) {
                    AppNavGraph(modifier = Modifier.padding(it), navigationViewModel = navigationViewModel)
                }
            }
        }
    }
}