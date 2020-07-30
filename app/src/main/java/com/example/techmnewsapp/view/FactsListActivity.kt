package com.example.techmnewsapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.techmnewsapp.R
import com.example.techmnewsapp.data.api.ApiHelperImpl
import com.example.techmnewsapp.data.api.ApiService
import com.example.techmnewsapp.data.local.AppDatabase
import com.example.techmnewsapp.data.local.DatabaseHelperImpl
import com.example.techmnewsapp.data.model.Facts
import com.example.techmnewsapp.databinding.ActivityNewsListBinding
import com.example.techmnewsapp.utils.Status
import com.example.techmnewsapp.utils.isNetworkConnected
import com.example.techmnewsapp.viewmodel.FactViewModel
import com.example.techmnewsapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_news_list.*
import org.koin.android.ext.android.inject

class FactsListActivity : AppCompatActivity() {

    private lateinit var activityNewsListBinding: ActivityNewsListBinding
    private lateinit var factViewModel: FactViewModel
    private val apiService: ApiService by inject()
    private val appDatabase: AppDatabase by inject()
    private val actionBar by lazy {
        supportActionBar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNewsListBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_news_list
        )
        setUpViewModel()
        setUpRefreshLayout()
        activityNewsListBinding.factsListAdapter = FactsListAdapter()
        activityNewsListBinding.factViewModel = factViewModel

        loadData()
        setUpObserver()
    }

    private fun loadData() {
        if (isNetworkConnected()) {
            factViewModel.fetchFactsApi()
        } else {
            factViewModel.fetchFactsDB(this)
        }
    }

    private fun setUpRefreshLayout() {
        pullToRefresh.setOnRefreshListener {
            pullToRefresh.isRefreshing = true
            loadData()
        }
    }

    /**
     * used to set observer for the live data object
     */
    private fun setUpObserver() {
        factViewModel.baseResponseLiveData.observe(this, Observer {
            pullToRefresh.isRefreshing = false
            when (it.status) {
                Status.SUCCESS -> {
                    txt_error.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    actionBar?.let { actionBar ->
                        actionBar.title = it.data?.title
                    }
                    activityNewsListBinding.factsListAdapter?.setData(it.data?.facts as ArrayList<Facts>)
                }
                Status.ERROR -> {
                    txt_error.text = it.message
                    txt_error.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    txt_error.text = it.message
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    txt_error.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
            }

        })
    }


    /**
     * used to initialize the news view model for the activity
     */
    private fun setUpViewModel() {
        factViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(apiService),
                DatabaseHelperImpl(appDatabase)
            )
        ).get(FactViewModel::class.java)
    }
}