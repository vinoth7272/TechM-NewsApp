package com.example.techmnewsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.techmnewsapp.data.api.ApiHelperImpl
import com.example.techmnewsapp.data.local.DatabaseHelperImpl

class ViewModelFactory(private val apiHelperImpl: ApiHelperImpl,private val databaseHelperImpl: DatabaseHelperImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FactViewModel::class.java))
            return FactViewModel(apiHelperImpl,databaseHelperImpl) as T
        throw IllegalStateException("Unknown class name")
    }

}
