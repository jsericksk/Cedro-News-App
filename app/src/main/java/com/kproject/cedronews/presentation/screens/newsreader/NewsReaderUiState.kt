package com.kproject.cedronews.presentation.screens.newsreader

import com.kproject.cedronews.commom.ResultState
import com.kproject.cedronews.domain.model.NewsContentModel

data class NewsReaderUiState(
    val dataState: ResultState<NewsContentModel> = ResultState.Loading
)