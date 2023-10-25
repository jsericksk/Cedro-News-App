package com.kproject.cedronews.presentation.screens.newsreader

import com.kproject.cedronews.commom.ResultState
import com.kproject.cedronews.presentation.screens.newsreader.model.NewsContent

data class NewsReaderUiState(
    val dataState: ResultState<NewsContent> = ResultState.Loading
)