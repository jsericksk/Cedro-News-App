package com.kproject.cedronews.presentation.navigation

const val NewsIdKey = "newsId"

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object NewsReaderScreen : Screen("news_reader_screen/{$NewsIdKey}") {
        fun createRoute(newsId: Int) = "news_reader_screen/$newsId"
    }
}