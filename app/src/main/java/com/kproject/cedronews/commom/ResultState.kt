package com.kproject.cedronews.commom

sealed class ResultState<out T>(val result: T? = null) {
    data object Loading : ResultState<Nothing>()
    data class Success<T>(val data: T? = null) : ResultState<T>(result = data)
    data class Error<T>(val exception: Exception? = null) : ResultState<T>()
}