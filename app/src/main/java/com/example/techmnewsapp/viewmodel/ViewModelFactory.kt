package com.example.techmnewsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.techmnewsapp.data.api.ApiHelper
import com.example.techmnewsapp.data.api.ApiHelperImpl
import com.example.techmnewsapp.data.local.DatabaseHelper
import com.example.techmnewsapp.data.local.DatabaseHelperImpl

class ViewModelFactory(
    private val factRepository: FactRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FactViewModel::class.java))
            return FactViewModel(factRepository) as T
        else
            throw IllegalArgumentException("Unknown viewmodel class")
    }
}