package com.example.aptoidedemo.presentation.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aptoidedemo.presentation.ui.screens.details.DetailsScreen
import com.example.aptoidedemo.presentation.ui.screens.landing.LandingScreen


enum class NavigationRoute(val route: String) {
    LANDING_SCREEN("landing"),
    DETAILS_SCREEN("details/{id}"),
}

@Composable
fun MainNavigation(startDestination: String = NavigationRoute.LANDING_SCREEN.route, onGranted: () -> Unit = {}) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = NavigationRoute.LANDING_SCREEN.route) {
            LandingScreen(
                onGranted = onGranted
            ) {
                navController.navigate("details/$it") {
                    launchSingleTop = true
                }
            }
        }
        composable(
            route = NavigationRoute.DETAILS_SCREEN.route,
            arguments = listOf(navArgument("id") { type = NavType.LongType }),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(1000)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(1000)
                )
            }
        ) {
            DetailsScreen(id = it.arguments?.getLong("id") ?: 0,) {
                navController.popBackStackLocal()
            }
        }
    }
}

internal fun NavController.popBackStackLocal(route: String = NavigationRoute.LANDING_SCREEN.route) {
    this.navigate(route) {
        popUpTo(route) { inclusive = true }
        launchSingleTop = true
    }
}
