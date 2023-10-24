package com.kproject.cedronews.presentation.screens.home.model

import com.kproject.cedronews.domain.model.NewsModel

data class News(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val category: String,
    val date: String,
    val elapsedTime: String
)

fun NewsModel.fromModel() = News(id, title, imageUrl, category, date, elapsedTime)