package com.example.sistemabienestarpersonal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.sistemabienestarpersonal.ui.screen.*
<<<<<<< HEAD
=======
import com.example.sistemabienestarpersonal.viewmodel.AuthViewModel
import com.example.sistemabienestarpersonal.viewmodel.WellbeingViewModel
>>>>>>> Mario

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    
    // Instanciamos el ViewModel aquí para que viva durante toda la navegación.
    // Esto es CLAVE para la rúbrica de "Manejo de estado persistente".
    val viewModel: WellbeingViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = SplashRoute
    ) {
        composable<SplashRoute> {
            SplashScreen(
                onNavigateNext = {
<<<<<<< HEAD
                    navController.navigate(WelcomeRoute) {
=======
                    navController.navigate(AuthRoute) {
>>>>>>> Mario
                        popUpTo(SplashRoute) { inclusive = true }
                    }
                }
            )
        }

<<<<<<< HEAD
        composable<WelcomeRoute> {
            WelcomeScreen(
                onStartEvaluation = { navController.navigate(TestRoute) },
                onViewHistory = { navController.navigate(HistoryRoute) }
=======
        composable<AuthRoute> {
            val authViewModel: AuthViewModel = viewModel()
            AuthScreen(
                viewModel = authViewModel,
                onAuthSuccess = {
                    navController.navigate(WelcomeRoute) {
                        popUpTo(AuthRoute) { inclusive = true }
                    }
                }
            )
        }

        composable<WelcomeRoute> {
            WelcomeScreen(
                onStartEvaluation = {
                    // Cargamos los escenarios de la API falsa antes de navegar
                    viewModel.cargarEscenarios()
                    navController.navigate(TestRoute)
                },
                onViewHistory = { navController.navigate(HistoryRoute) },
                onViewProfile = { navController.navigate(ProfileRoute) }
>>>>>>> Mario
            )
        }

        composable<TestRoute> {
<<<<<<< HEAD
            TestScreen(
=======
            // Le pasamos el ViewModel a la pantalla de Test
            TestScreen(
                viewModel = viewModel,
>>>>>>> Mario
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
<<<<<<< HEAD
                interpretation = args.scoreSummary,
=======
                interpretation = args.interpretation,
>>>>>>> Mario
                onNavigateHome = {
                    navController.navigate(WelcomeRoute) {
                        popUpTo(WelcomeRoute) { inclusive = true }
                    }
                }
            )
        }

        composable<HistoryRoute> {
            HistoryScreen(
<<<<<<< HEAD
=======
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        
        composable<ProfileRoute> {
            ProfileScreen(
                viewModel = viewModel,
>>>>>>> Mario
                onBack = { navController.popBackStack() }
            )
        }
    }
}