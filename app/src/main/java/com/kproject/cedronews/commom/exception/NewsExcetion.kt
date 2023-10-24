package com.kproject.cedronews.commom.exception

sealed class NewsException : Exception() {
    data object FailedToLoad : NewsException()
    data object UnknownError : NewsException()
}