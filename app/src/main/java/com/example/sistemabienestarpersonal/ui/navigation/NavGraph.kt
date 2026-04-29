package com.example.sistemabienestarpersonal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sistemabienestarpersonal.ui.screen.ResultScreen
import com.example.sistemabienestarpersonal.ui.screen.TestScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "test") {

        composable("test") {
            TestScreen(navController)
        }

        composable("result/{data}") { backStackEntry ->
            val data = backStackEntry.arguments?.getString("data") ?: ""
            ResultScreen(data, navController)
        }
    }
}