package com.kproject.cedronews.data.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kproject.cedronews.commom.ResultState
import com.kproject.cedronews.commom.exception.NewsException
import com.kproject.cedronews.data.remote.apiservice.NewsApiService
import com.kproject.cedronews.data.remote.model.toModel
import com.kproject.cedronews.data.remote.paging.NewsApiPagingSource
import com.kproject.cedronews.domain.model.NewsContentModel
import com.kproject.cedronews.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val newsApiService: NewsApiService
) : NewsRepository {

    override fun getAllNews(): Flow<PagingData<NewsModel>> {
        return Pager(
            config = PagingConfig(pageSize = 12, prefetchDistance = 1),
            pagingSourceFactory = {
                NewsApiPagingSource(newsApiService = newsApiService)
            }
        ).flow
    }

    override fun getNewsById(id: Int): Flow<ResultState<NewsContentModel>> = flow {
        try {
            emit(ResultState.Loading)
            val response = newsApiService.getNewsById(id)
            if (response.isSuccessful) {
                response.body()?.let { newsContentResponse ->
                    emit(ResultState.Success(newsContentResponse.toModel()))
                } ?: emit(ResultState.Error(NewsException.FailedToLoad))
            } else {
                emit(ResultState.Error(NewsException.FailedToLoad))
            }
        } catch (e: Exception) {
            emit(ResultState.Error(NewsException.UnknownError))
        }
    }
}