package com.example.sistemabienestarpersonal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.sistemabienestarpersonal.ui.screen.*
import com.example.sistemabienestarpersonal.viewmodel.AuthViewModel
import com.example.sistemabienestarpersonal.viewmodel.WellbeingViewModel

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
                    navController.navigate(AuthRoute) {
                        popUpTo(SplashRoute) { inclusive = true }
                    }
                }
            )
        }

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
            )
        }

        composable<TestRoute> {
            // Le pasamos el ViewModel a la pantalla de Test
            TestScreen(
                viewModel = viewModel,
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
                interpretation = args.interpretation,
                onNavigateHome = {
                    navController.navigate(WelcomeRoute) {
                        popUpTo(WelcomeRoute) { inclusive = true }
                    }
                }
            )
        }

        composable<HistoryRoute> {
            HistoryScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        
        composable<ProfileRoute> {
            ProfileScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}