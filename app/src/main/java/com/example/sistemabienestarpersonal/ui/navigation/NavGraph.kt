package com.example.sistemabienestarpersonal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.sistemabienestarpersonal.ui.screen.*

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashRoute
    ) {
        composable<SplashRoute> {
            SplashScreen(
                onNavigateNext = {
                    navController.navigate(WelcomeRoute) {
                        popUpTo(SplashRoute) { inclusive = true }
                    }
                }
            )
        }

        composable<WelcomeRoute> {
            WelcomeScreen(
                onStartEvaluation = { navController.navigate(TestRoute) },
                onViewHistory = { navController.navigate(HistoryRoute) }
            )
        }

        composable<TestRoute> {
            TestScreen(
                onFinish = { interpretation ->
                    navController.navigate(ResultRoute(interpretation)) {
                        popUpTo(TestRoute) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable<ResultRoute> { backStackEntry ->
            val args = backStackEntry.toRoute<ResultRoute>()
            ResultScreen(
                interpretation = args.scoreSummary,
                onNavigateHome = {
                    navController.navigate(WelcomeRoute) {
                        popUpTo(WelcomeRoute) { inclusive = true }
                    }
                }
            )
        }

        composable<HistoryRoute> {
            HistoryScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}