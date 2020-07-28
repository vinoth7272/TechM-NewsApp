package com.example.techmnewsapp.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("rows")
    val facts: List<Facts>
)