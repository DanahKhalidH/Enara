package com.example.enarah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.enarah.navigation.EnarahNavGraph
import com.example.enarah.ui.theme.EnarahTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnarahTheme {
                val navController = rememberNavController()
                EnarahNavGraph(navController = navController)
            }
        }
    }
}