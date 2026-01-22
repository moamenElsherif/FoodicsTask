package com.app.foodicstask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.app.foodicstask.ui.navigation.AppNavGraph
import com.app.foodicstask.ui.navigation.BottomNavigationBar
import com.app.foodicstask.ui.navigation.NavigationViewModel
import com.app.foodicstask.ui.theme.FoodicsTaskTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodicsTaskTheme {
                val navigationViewModel: NavigationViewModel = koinViewModel()
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
