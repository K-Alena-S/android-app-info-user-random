package com.example.android_app_info_user_random.di

import com.example.android_app_info_user_random.data.network.ApiService
import com.example.android_app_info_user_random.data.repository.UserRepository
import com.example.android_app_info_user_random.viewmodel.UserViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    val okHttpClient = OkHttpClient.Builder()
        .followRedirects(true)
        .build()

    single {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }
    single { UserRepository(get(), androidContext()) }
    viewModel { UserViewModel(get()) }
}