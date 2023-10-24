package com.kproject.cedronews.data.repository

import androidx.paging.PagingData
import com.kproject.cedronews.commom.ResultState
import com.kproject.cedronews.domain.model.NewsContentModel
import com.kproject.cedronews.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getAllNews(): Flow<PagingData<NewsModel>>

    fun getNewsById(id: Int): Flow<ResultState<NewsContentModel>>
}