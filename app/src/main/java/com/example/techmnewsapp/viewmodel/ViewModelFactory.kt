package com.example.techmnewsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java))
            return NewsViewModel() as T
        throw IllegalStateException("Unknown class name")
    }

}
