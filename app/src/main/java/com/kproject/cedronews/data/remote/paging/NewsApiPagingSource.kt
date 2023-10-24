package com.kproject.cedronews.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kproject.cedronews.commom.exception.NewsException
import com.kproject.cedronews.data.remote.apiservice.NewsApiService
import com.kproject.cedronews.data.remote.model.toModel
import com.kproject.cedronews.domain.model.NewsModel

class NewsApiPagingSource(
    private val newsApiService: NewsApiService
) : PagingSource<Int, NewsModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsModel> {
        return try {
            val page = params.key ?: 0
            val response = newsApiService.getAllNews(page = page)
            if (response.isSuccessful) {
                response.body()?.let { infoNewsResponse ->
                    val newsModel = infoNewsResponse.news.map { newsResponse ->
                        newsResponse.toModel()
                    }
                    return LoadResult.Page(
                        data = newsModel,
                        prevKey = if (page == 1) null else page.minus(1),
                        nextKey = if (page < infoNewsResponse.pages) page.plus(1) else null
                    )
                }
            } else {
                return LoadResult.Error(NewsException.FailedToLoad)
            }
            LoadResult.Error(NewsException.FailedToLoad)
        } catch (e: Exception) {
            LoadResult.Error(NewsException.UnknownError)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}