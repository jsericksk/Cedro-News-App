package com.kproject.cedronews.data.remote.model

import com.kproject.cedronews.domain.model.NewsModel

data class InfoNewsResponse(
    val pages: Int,
    val news: List<NewsResponse>
)

data class NewsResponse(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val category: String,
    val date: String,
    val elapsedTime: String
)

fun NewsResponse.toModel() = NewsModel(id, title, imageUrl, category, date, elapsedTime)