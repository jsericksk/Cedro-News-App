package com.kproject.cedronews.domain.model

data class NewsContentModel(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val content: String,
    val date: String
)