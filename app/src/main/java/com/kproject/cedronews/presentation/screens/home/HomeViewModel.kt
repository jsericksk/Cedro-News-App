package com.kproject.cedronews.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.kproject.cedronews.data.repository.remote.NewsRepository
import com.kproject.cedronews.presentation.screens.home.model.News
import com.kproject.cedronews.presentation.screens.home.model.fromModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel"

class HomeViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _news = MutableStateFlow<PagingData<News>>(PagingData.empty())
    val news: StateFlow<PagingData<News>> = _news

    private fun getAllNews() {
        viewModelScope.launch {
            newsRepository.getAllNews().cachedIn(viewModelScope)
                .collect { pagingDataModel ->
                    _news.value = pagingDataModel.map { newsModel -> newsModel.fromModel() }
                }
        }
    }
}