package com.example.techmnewsapp.viewmodel

import androidx.lifecycle.Observer
import com.example.techmnewsapp.TestCoroutineRule
import com.example.techmnewsapp.data.api.ApiHelper
import com.example.techmnewsapp.data.model.BaseResponse
import com.example.techmnewsapp.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FactViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var apiResponseObserver: Observer<Resource<BaseResponse>>

    @Before

    fun setUp() {
    }

    @Test
    fun fetchFactsApiSuccess() {
    }

    @Test
    fun fetchFactsDB() {
    }
}