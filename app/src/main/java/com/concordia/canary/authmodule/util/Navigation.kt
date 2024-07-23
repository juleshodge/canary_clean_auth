package com.concordia.canary.authmodule.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.concordia.canary.authmodule.presentation.FinalDestinationScreen
import com.concordia.canary.authmodule.presentation.LoginScreen
import com.concordia.canary.authmodule.presentation.RegisterScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.LoginScreenRoute.route,
        modifier = modifier
    ) {
        composable(ScreenRoutes.LoginScreenRoute.route) {
            LoginScreen(
                onLoginSuccessNavigation = {
                    navController.navigate(ScreenRoutes.FinalDestination.route) {
                        popUpTo(0)
                    }
                },
                onNavigateToRegisterScreen = {
                    navController.navigate(ScreenRoutes.RegisterScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }

        composable(ScreenRoutes.RegisterScreen.route) {
            RegisterScreen(
                onRegisterSuccessNavigation = {
                    navController.navigate(ScreenRoutes.FinalDestination.route) {
                        popUpTo(0)
                    }
                },
                onNavigateToLoginScreen = {
                    navController.navigate(ScreenRoutes.LoginScreenRoute.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(ScreenRoutes.FinalDestination.route) {
            FinalDestinationScreen()
        }
    }
}

sealed class ScreenRoutes(val route: String) {
    data object LoginScreenRoute : ScreenRoutes("login_screen/")
    data object RegisterScreen : ScreenRoutes("register_screen/")
    data object FinalDestination : ScreenRoutes("final_screen/")
}