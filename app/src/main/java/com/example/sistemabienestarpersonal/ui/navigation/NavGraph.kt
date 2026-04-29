package com.example.sistemabienestarpersonal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "welcome") {

        composable("welcome") {
            WelcomeScreen(navController)
        }

        composable("test") {
            TestScreen(navController)
        }

        composable("result") {
            ResultScreen(navController)
        }

        composable("home") {
            HomeScreen()
        }
    }
}