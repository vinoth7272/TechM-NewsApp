package com.example.techmnewsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmnewsapp.data.api.Resource
import com.example.techmnewsapp.data.model.BaseResponse
import kotlinx.coroutines.launch


class FactViewModel(
    private val factRepository: FactRepository
) : ViewModel() {

    var baseResponseLiveData = MutableLiveData<Resource<BaseResponse>>()

    fun fetchFactsApi() {
        baseResponseLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            baseResponseLiveData.postValue(factRepository.fetchFactDataFromApi())
        }
    }

    fun fetchFactsDB() {
        baseResponseLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            baseResponseLiveData.postValue(factRepository.getFactsDataFromDB())
        }
    }
}