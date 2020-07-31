package com.example.techmnewsapp.view

import com.example.techmnewsapp.viewmodel.FactRepository
import com.example.techmnewsapp.viewmodel.FactViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FactsListActivityTest {
    @Mock
    private lateinit var factsListActivity: FactsListActivity

    private lateinit var viewModel: FactViewModel

    @Before
    fun setUp() {
        factsListActivity = Mockito.mock(FactsListActivity::class.java)
        val factRepository = Mockito.mock(FactRepository::class.java)
        viewModel = FactViewModel(factRepository)

    }

    @Test
    fun loadErrorDataTest() {
//        viewModel.baseResponseLiveData.observe()
    }

}