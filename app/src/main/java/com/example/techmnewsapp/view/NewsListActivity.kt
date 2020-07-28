package com.example.techmnewsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.techmnewsapp.R
import com.example.techmnewsapp.viewmodel.NewsViewModel
import com.example.techmnewsapp.viewmodel.ViewModelFactory

class ListActivity : AppCompatActivity() {


    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        newsViewModel= ViewModelProvider(this, ViewModelFactory()).get(NewsViewModel::class.java)
    }
}