package com.kproject.cedronews.domain.model

data class NewsModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val category: String,
    val date: String,
    val elapsedTime: String
)