package com.ovinkin.practice3_jsonplaceholder.di

import com.ovinkin.practice3_jsonplaceholder.data.api.JSONPlaceholderApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

val networkModule = module {
    factory { provideRetrofit() }
    single { provideNetworkApi(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()
}

fun provideNetworkApi(retrofit: Retrofit): JSONPlaceholderApi =
    retrofit.create(JSONPlaceholderApi::class.java)