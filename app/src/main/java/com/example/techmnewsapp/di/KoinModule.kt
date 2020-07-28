package com.example.techmnewsapp.di

import com.example.techmnewsapp.data.api.ApiService
import com.example.techmnewsapp.utils.Constant.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val retrofitModule = module {
    single {
        getApiSevice()
    }
}

fun getApiSevice(): ApiService {
    return Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
}
