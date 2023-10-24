package com.kproject.cedronews.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kproject.cedronews.presentation.screens.home.HomeScreen
import com.kproject.cedronews.presentation.screens.home.HomeViewModel

@Composable
fun NavigationGraph(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                homeViewModel = homeViewModel,
                onNavigateToNewsReaderScreen = { newsId ->

                }
            )
        }
    }
}