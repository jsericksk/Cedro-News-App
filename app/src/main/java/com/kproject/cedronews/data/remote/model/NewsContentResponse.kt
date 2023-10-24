package com.kproject.cedronews.data.remote.model

import com.kproject.cedronews.domain.model.NewsContentModel

data class NewsContentResponse(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val content: String,
    val date: String
)

fun NewsContentResponse.toModel() = NewsContentModel(id, title, subtitle, imageUrl, content, date)