package com.example.techmnewsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


fun Context.isNetworkConnected(): Boolean {
    val connectivityManager: ConnectivityManager? =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
}

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.adapter = adapter
        this.visibility = View.VISIBLE
    }
}

@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(imageUrl: String?) {
    if (imageUrl != null && imageUrl.isNotEmpty()) {
        Log.d("TAG", imageUrl)
        this.visibility = View.VISIBLE
        val picasso = Picasso.get()
        picasso.isLoggingEnabled()
        picasso.load(imageUrl).fit().into(this)
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter(value = ["setText"])
fun TextView.bindText(value: String?) {
    if (value != null && value.isNotEmpty()) {
        this.text = value
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}