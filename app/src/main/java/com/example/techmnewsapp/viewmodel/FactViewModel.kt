package com.example.techmnewsapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmnewsapp.R
import com.example.techmnewsapp.data.api.ApiHelper
import com.example.techmnewsapp.data.local.DatabaseHelper
import com.example.techmnewsapp.data.model.BaseResponse
import com.example.techmnewsapp.data.model.Facts
import com.example.techmnewsapp.utils.Resource
import com.example.techmnewsapp.view.FactsListActivity
import kotlinx.coroutines.launch

class FactViewModel(
    private val apiHelper: ApiHelper,
    private val databaseHelper: DatabaseHelper
) : ViewModel() {

    var baseResponseLiveData = MutableLiveData<Resource<BaseResponse>>()

    fun fetchFactsApi() {
        baseResponseLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            val response = apiHelper.getFact()
            if (response.isSuccessful) {
                val responseBody = response.body() as BaseResponse
                baseResponseLiveData.postValue(Resource.success(responseBody))
                databaseHelper.deleteAll()
                val factsList = ArrayList<Facts>()
                responseBody.facts.forEachIndexed { index, value ->
                    val fact = Facts(
                        index, responseBody.title, value.title, value.description, value.imageUrl
                    )
                    factsList.add(fact)
                }
                databaseHelper.insertAll(factsList)
            } else {
                baseResponseLiveData.postValue(Resource.error(response.message(), null))
            }
        }
    }

    fun fetchFactsDB(activity: FactsListActivity) {
        baseResponseLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            val factList = databaseHelper.getAllFacts()
            Log.d("TAG", "Data - > ${factList.size} || $factList")
            if (factList.isNotEmpty()) {
                val newsTitle = factList.get(0).newsTitle!!
                val baseResponse = BaseResponse(newsTitle, factList)
                baseResponseLiveData.postValue(Resource.success(baseResponse))
            } else {
                baseResponseLiveData.postValue(
                    Resource.error(
                        activity.getString(R.string.network_error),
                        null
                    )
                )
            }
        }
    }
}