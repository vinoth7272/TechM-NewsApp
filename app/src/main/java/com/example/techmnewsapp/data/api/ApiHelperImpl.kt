package com.example.techmnewsapp.data.api

import com.example.techmnewsapp.data.model.BaseResponse
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getFact(): Response<BaseResponse> = apiService.getFacts()
}