package com.kproject.cedronews.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.kproject.cedronews.commom.ResultState
import com.kproject.cedronews.data.repository.NewsRepository
import com.kproject.cedronews.presentation.screens.home.model.fromModel
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel"

class HomeViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private fun getAllNews() {
        viewModelScope.launch {
            newsRepository.getAllNews().cachedIn(viewModelScope)
                .collect { pagingDataModel ->
                    val news = pagingDataModel.map { newsModel -> newsModel.fromModel() }
                }
        }
    }

    fun getNewsById(id: Int) {
        viewModelScope.launch {
            newsRepository.getNewsById(id).collect { result ->
                when (result) {
                    ResultState.Loading -> {

                    }
                    is ResultState.Success -> {

                    }
                    is ResultState.Error -> {

                    }
                }
            }
        }
    }
}