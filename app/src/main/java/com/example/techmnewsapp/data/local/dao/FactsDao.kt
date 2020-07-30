package com.example.techmnewsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.techmnewsapp.data.model.Facts

@Dao
interface FactsDao {

    @Query("SELECT * FROM Facts")
    suspend fun getAll(): List<Facts>

    @Insert
    suspend fun insertAll(users: List<Facts>)

    @Query("DELETE FROM Facts")
    suspend fun deleteAll()
}