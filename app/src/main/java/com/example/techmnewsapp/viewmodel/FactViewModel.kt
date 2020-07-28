package com.example.techmnewsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmnewsapp.data.api.ApiHelper
import com.example.techmnewsapp.data.model.BaseResponse
import com.example.techmnewsapp.utils.Resource
import kotlinx.coroutines.launch

class FactViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    var baseResponse = MutableLiveData<Resource<BaseResponse>>()

    fun fetchFacts() {
        baseResponse.postValue(Resource.loading(null))
        viewModelScope.launch {
            val response = apiHelper.getFact()
            if (response.isSuccessful) {
                val responseBody = response.body()
                baseResponse.postValue(Resource.success(responseBody as BaseResponse))
            } else {
                baseResponse.postValue(Resource.error(response.message(), null))
            }
        }
    }
}