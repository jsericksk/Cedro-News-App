package com.kproject.cedronews.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kproject.cedronews.presentation.screens.home.HomeScreen
import com.kproject.cedronews.presentation.screens.home.HomeViewModel
import com.kproject.cedronews.presentation.screens.newsreader.NewsReaderScreen

@Composable
fun NavigationGraph(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                homeViewModel = homeViewModel,
                onNavigateToNewsReaderScreen = { newsId ->
                    navController.navigate(
                        Screen.NewsReaderScreen.createRoute(newsId)
                    ) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = Screen.NewsReaderScreen.route,
            arguments = listOf(
                navArgument(name = NewsIdKey) {
                    type = NavType.IntType
                },
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(700)
                )
            }
        ) { backStackEntry ->
            NewsReaderScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}