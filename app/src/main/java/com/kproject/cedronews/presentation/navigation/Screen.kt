package com.kproject.cedronews.presentation.navigation

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object NewsReaderScreen : Screen("news_reader_screen")
}