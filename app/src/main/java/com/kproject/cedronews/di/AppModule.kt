package com.kproject.cedronews.di

import com.kproject.cedronews.data.remote.apiservice.NewsApiService
import com.kproject.cedronews.data.repository.prefs.PreferenceRepository
import com.kproject.cedronews.data.repository.prefs.PreferenceRepositoryImpl
import com.kproject.cedronews.data.repository.remote.NewsRepository
import com.kproject.cedronews.data.repository.remote.NewsRepositoryImpl
import com.kproject.cedronews.presentation.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel {
        HomeViewModel(
            newsRepository = get(),
            preferenceRepository = get()
        )
    }
}

val repositoryModule = module {
    single<NewsRepository> { NewsRepositoryImpl(newsApiService = get()) }
    single<PreferenceRepository> { PreferenceRepositoryImpl(context = get()) }
}

val networkModule = module {
    single { createNewsApiService() }
}

private fun createNewsApiService(): NewsApiService {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(NewsApiService.BaseUrl)
        .build()
    return retrofit.create(NewsApiService::class.java)
}