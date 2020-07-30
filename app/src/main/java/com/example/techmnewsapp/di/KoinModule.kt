package com.example.techmnewsapp.di

import android.content.Context
import androidx.room.Room
import com.example.techmnewsapp.data.api.ApiService
import com.example.techmnewsapp.data.local.AppDatabase
import com.example.techmnewsapp.utils.Constant
import com.example.techmnewsapp.utils.Constant.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val retrofitModule = module {
    single {
        getApiSevice()

    }
}
val databaseModule = module {
    single {
        getDBInstance(get())
    }
}

fun getApiSevice(): ApiService {
    return Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
}

fun getDBInstance(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, Constant.DB_NAME).build()
}

