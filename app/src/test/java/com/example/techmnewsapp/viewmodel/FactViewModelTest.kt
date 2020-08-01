package com.example.techmnewsapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.techmnewsapp.TestCoroutineRule
import com.example.techmnewsapp.data.api.ApiHelper
import com.example.techmnewsapp.data.api.Resource
import com.example.techmnewsapp.data.model.BaseResponse
import com.example.techmnewsapp.data.model.Facts
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @FactViewModelTest class used to test the @FactViewModel and its method
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FactViewModelTest {

    private lateinit var viewModel: FactViewModel

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var factRepository: FactRepository

    private val mockFactsList = ArrayList<Facts>()

    private lateinit var mockBaseResponse: BaseResponse

    private lateinit var mockSuccessResource: Resource<BaseResponse>


    @Mock
    private lateinit var apiResponseObserver: Observer<Resource<BaseResponse>>

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        mockFactsList.add(Facts(0, "", "Test", "Test", "Test"))
        mockBaseResponse = BaseResponse("Test Title", mockFactsList)
        mockSuccessResource = Resource.success(mockBaseResponse)
        viewModel = FactViewModel(factRepository)
    }

    /**
     * method to test the API Success response
     */
    @Test
    fun fetchFactsApiSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(mockSuccessResource)
                .`when`(factRepository)
                .fetchFactDataFromApi()
            viewModel.baseResponseLiveData.observeForever(apiResponseObserver)
            delay(10)
            viewModel.fetchFactsApi()
            verify(apiResponseObserver, timeout(50)).onChanged(mockSuccessResource)
            viewModel.baseResponseLiveData.removeObserver(apiResponseObserver)
        }
    }

    /**
     * method to test the DB Success response
     */
    @Test
    fun fetchFactsDB() {
        testCoroutineRule.runBlockingTest {
            doReturn(mockSuccessResource)
                .`when`(factRepository)
                .getFactsDataFromDB()
            viewModel.baseResponseLiveData.observeForever(apiResponseObserver)
            delay(10)
            viewModel.fetchFactsDB()
            verify(apiResponseObserver, timeout(50)).onChanged(mockSuccessResource)
        }
    }
}