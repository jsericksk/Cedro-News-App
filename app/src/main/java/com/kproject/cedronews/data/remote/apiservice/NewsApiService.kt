package com.kproject.cedronews.data.remote.apiservice

import com.kproject.cedronews.data.remote.model.NewsContentResponse
import com.kproject.cedronews.data.remote.model.InfoNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApiService {

    @GET("/news")
    suspend fun getAllNews(@Query("page") page: Int?): Response<InfoNewsResponse>

    @GET("/news/{id}")
    suspend fun getNewsById(@Path("id") id: Int): Response<NewsContentResponse>

    companion object {
        const val BaseUrl = "https://cedro-news-api.onrender.com/"
    }
}