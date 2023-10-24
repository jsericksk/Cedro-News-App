package com.kproject.cedronews.presentation.screens.home

sealed class HomeUiEvent {
    data object ChangeAppTheme : HomeUiEvent()
    data object KeepSplashOnScreenChanged : HomeUiEvent()
}