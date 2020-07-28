package com.example.techmnewsapp.data.model

import com.google.gson.annotations.SerializedName

data class Facts(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imageHref")
    val imageUrl: String
)