package com.example.aptoidedemo.presentation.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aptoidedemo.presentation.ui.details.DetailsScreen
import com.example.aptoidedemo.presentation.ui.landing.LandingScreen


enum class NavigationRoute(val route: String) {
    LANDING_SCREEN("landing"),
    DETAILS_SCREEN("details"),
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationRoute.LANDING_SCREEN.route) {
        composable(route = NavigationRoute.LANDING_SCREEN.route) {
            LandingScreen {
                navController.navigate(NavigationRoute.DETAILS_SCREEN.route) {
                    launchSingleTop = true
                }
            }
        }
        composable(
            route = NavigationRoute.DETAILS_SCREEN.route,
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
            DetailsScreen(modifier = Modifier.padding(16.dp)) {
                navController.popBackStackLocal()
            }
        }
    }
}

internal fun NavController.popBackStackLocal(route: String = NavigationRoute.LANDING_SCREEN.route){
    this.navigate(route) {
        popUpTo(route) { inclusive = true }
        launchSingleTop = true
    }
}
