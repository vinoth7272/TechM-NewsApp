package com.example.techmnewsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Facts(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "newsTitle")
    val newsTitle: String?,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String?,
    @ColumnInfo(name = "imageHref")
    @SerializedName("imageHref")
    val imageUrl: String?
)