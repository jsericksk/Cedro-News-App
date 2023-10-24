package com.kproject.cedronews.presentation.screens.home

import androidx.paging.PagingData
import com.kproject.cedronews.presentation.screens.home.model.News

data class HomeUiState(
    val keepSplashOnScreen: Boolean = true,
    val isDarkMode: Boolean = true,
    val pagingData: PagingData<News> = PagingData.empty()
)