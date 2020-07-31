package com.example.techmnewsapp.viewmodel

import android.content.Context
import com.example.techmnewsapp.R
import com.example.techmnewsapp.TestCoroutineRule
import com.example.techmnewsapp.data.api.ApiHelper
import com.example.techmnewsapp.data.api.Resource
import com.example.techmnewsapp.data.local.DatabaseHelper
import com.example.techmnewsapp.data.model.BaseResponse
import com.example.techmnewsapp.data.model.Facts
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FactRepositoryTest {

    @Mock
    private lateinit var factRepository: FactRepository

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var databaseHelper: DatabaseHelper


    private val mockSuccessFactList = ArrayList<Facts>()
    private val mockEmptyFactList = ArrayList<Facts>()

    private lateinit var mockBaseResponse: BaseResponse
    private lateinit var retrofitSuccessResponse: Response<BaseResponse>
    private lateinit var retrofitErrorResponse: Response<BaseResponse>

    private lateinit var mockResource: Resource<BaseResponse>


    @Before
    fun setUp() {
        apiHelper = mock(ApiHelper::class.java)
        databaseHelper = mock(DatabaseHelper::class.java)
        factRepository = mock(FactRepository::class.java)
        context = mock(Context::class.java)
        factRepository = FactRepository(
            apiHelper = apiHelper,
            databaseHelper = databaseHelper,
            context = context
        )
    }

    /**
     * Method used to test the API Success responses
     */
    @Test
    fun fetchFactDataFromApiSuccess() {
        mockSuccessFactList.add(Facts(0, "", "Test", "Test", "Test"))
        mockBaseResponse = BaseResponse("Test Title", mockSuccessFactList)
        mockResource = Resource.success(mockBaseResponse)
        retrofitSuccessResponse = Response.success(mockBaseResponse)
        testCoroutineRule.runBlockingTest {
            doReturn(retrofitSuccessResponse)
                .`when`(apiHelper)
                .getFact()
            delay(10)
            factRepository.fetchFactDataFromApi()
            assertEquals(mockResource, factRepository.fetchFactDataFromApi())
        }

    }

    /**
     * Method used to test the API Error responses
     */
    @Test
    fun fetchFactDataFromApiError() {
        retrofitErrorResponse =
            Response.error(400, ResponseBody.create(MediaType.parse("JSON"), "Unauthorised"))
        val errorResponse = Resource.error("Unauthorised", null)
        testCoroutineRule.runBlockingTest {
            doReturn(retrofitErrorResponse)
                .`when`(apiHelper)
                .getFact()
            delay(10)
            factRepository.fetchFactDataFromApi()
            assertEquals(errorResponse, factRepository.fetchFactDataFromApi())
        }

    }

    /**
     * Method used to test the Database Success responses
     */
    @Test
    fun getFactsDataFromDBSuccess() {
        mockSuccessFactList.add(Facts(0, "", "Test", "Test", "Test"))
        mockBaseResponse = BaseResponse("", mockSuccessFactList)
        mockResource = Resource.success(mockBaseResponse)
        testCoroutineRule.runBlockingTest {
            doReturn(mockSuccessFactList)
                .`when`(databaseHelper)
                .getAllFacts()
            delay(10)
            factRepository.getFactsDataFromDB()
            assertEquals(mockResource, factRepository.getFactsDataFromDB())
        }
    }

    /**
     * Method used to test the Database Error responses
     */
    @Test
    fun getFactsDataFromDBError() {
        mockResource = Resource.error(context.getString(R.string.network_error), null)
        testCoroutineRule.runBlockingTest {
            doReturn(mockEmptyFactList)
                .`when`(databaseHelper)
                .getAllFacts()
            delay(10)
            factRepository.getFactsDataFromDB()
            assertEquals(mockResource, factRepository.getFactsDataFromDB())
        }
    }
}