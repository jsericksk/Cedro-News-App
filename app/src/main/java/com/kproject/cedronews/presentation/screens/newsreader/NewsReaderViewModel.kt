package com.kproject.cedronews.presentation.screens.newsreader

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kproject.cedronews.commom.ResultState
import com.kproject.cedronews.data.repository.remote.NewsRepository
import com.kproject.cedronews.presentation.navigation.NewsIdKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsReaderViewModel(
    private val newsRepository: NewsRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsReaderUiState())
    val uiState: StateFlow<NewsReaderUiState> get() = _uiState

    init {
        getNewsById()
    }

    private fun getNewsById() {
        viewModelScope.launch {
            val newsId = savedStateHandle.get<Int>(NewsIdKey) ?: -1
            newsRepository.getNewsById(newsId).collect { result ->
                when (result) {
                    ResultState.Loading -> {
                        _uiState.update {
                            it.copy(dataState = ResultState.Loading)
                        }
                    }
                    is ResultState.Success -> {
                        result.result?.let { newsContentModel ->
                            _uiState.update {
                                it.copy(dataState = ResultState.Success(newsContentModel))
                            }
                        }
                    }
                    is ResultState.Error -> {
                        _uiState.update {
                            it.copy(dataState = ResultState.Error())
                        }
                    }
                }
            }
        }
    }
}