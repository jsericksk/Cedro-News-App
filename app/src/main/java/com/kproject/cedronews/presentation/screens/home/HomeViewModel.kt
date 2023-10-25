package com.kproject.cedronews.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kproject.cedronews.commom.constants.PrefsConstants
import com.kproject.cedronews.data.repository.prefs.PreferenceRepository
import com.kproject.cedronews.data.repository.remote.NewsRepository
import com.kproject.cedronews.domain.model.NewsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel"

class HomeViewModel(
    private val newsRepository: NewsRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState

    private val _news = MutableStateFlow<PagingData<NewsModel>>(PagingData.empty())
    val news: StateFlow<PagingData<NewsModel>> = _news

    init {
        getValuesFromPrefs()
        getAllNews()
    }

    private fun getValuesFromPrefs() {
        val isDarkMode = preferenceRepository.getPreference(
            key = PrefsConstants.DarkMode,
            defaultValue = true
        )
        _uiState.update {
            it.copy(isDarkMode = isDarkMode)
        }
    }

    private fun getAllNews() {
        viewModelScope.launch {
            newsRepository.getAllNews().cachedIn(viewModelScope)
                .collect { pagingDataModel ->
                    _news.value = pagingDataModel
                }
        }
    }

    fun onUiEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.ChangeAppTheme -> {
                val isDarkMode = uiState.value.isDarkMode.not()
                preferenceRepository.savePreference(
                    key = PrefsConstants.DarkMode,
                    value = isDarkMode
                )
                _uiState.update { it.copy(isDarkMode = isDarkMode) }
            }
            HomeUiEvent.KeepSplashOnScreenChanged -> {
                _uiState.update { it.copy(keepSplashOnScreen = false) }
            }
        }
    }
}