package com.example.techmnewsapp.viewmodel

import android.content.Context
import com.example.techmnewsapp.R
import com.example.techmnewsapp.data.api.ApiHelper
import com.example.techmnewsapp.data.api.Resource
import com.example.techmnewsapp.data.local.DatabaseHelper
import com.example.techmnewsapp.data.model.BaseResponse
import com.example.techmnewsapp.data.model.Facts


class FactRepository(
    private val context: Context,
    private val apiHelper: ApiHelper,
    private val databaseHelper: DatabaseHelper
) {

    suspend fun fetchFactDataFromApi(): Resource<BaseResponse> {
        val baseResponseLiveData: Resource<BaseResponse>
        val response = apiHelper.getFact()
        baseResponseLiveData = if (response.isSuccessful) {
            val responseBody = response.body() as BaseResponse
            databaseHelper.deleteAll()
            val factsList = loadFactListToInsert(responseBody)
            databaseHelper.insertAll(factsList)
            Resource.success(responseBody)
        } else {
            Resource.error(response.message(), null)
        }
        return baseResponseLiveData
    }

    private fun loadFactListToInsert(responseBody: BaseResponse): ArrayList<Facts> {
        val factsList = ArrayList<Facts>()
        responseBody.facts.forEachIndexed { index, value ->
            val fact = Facts(
                index, responseBody.title, value.title, value.description, value.imageUrl
            )
            factsList.add(fact)
        }
        return factsList
    }

    suspend fun getFactsDataFromDB(): Resource<BaseResponse> {
        val baseResponseLiveData: Resource<BaseResponse>
        val factList = databaseHelper.getAllFacts()
        baseResponseLiveData = if (factList.isNotEmpty()) {
            val newsTitle = factList[0].newsTitle!!
            val baseResponse = BaseResponse(newsTitle, factList)
            Resource.success(baseResponse)
        } else {
            Resource.error(context.getString(R.string.network_error), null)
        }
        return baseResponseLiveData
    }
}