package com.example.techmnewsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.techmnewsapp.data.api.ApiHelperImpl

class ViewModelFactory(private val apiHelperImpl: ApiHelperImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FactViewModel::class.java))
            return FactViewModel(apiHelperImpl) as T
        throw IllegalStateException("Unknown class name")
    }

}
