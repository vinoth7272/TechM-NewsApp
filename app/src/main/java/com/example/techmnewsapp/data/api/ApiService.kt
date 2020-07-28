package com.example.techmnewsapp.data.api

import com.example.techmnewsapp.data.model.BaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun getFacts(): Response<BaseResponse>
}