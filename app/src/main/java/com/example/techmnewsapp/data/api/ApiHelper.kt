package com.example.techmnewsapp.data.api

import com.example.techmnewsapp.data.model.BaseResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getFact(): Response<BaseResponse>
}