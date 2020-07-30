package com.example.techmnewsapp.data.local

import com.example.techmnewsapp.data.model.Facts

interface DatabaseHelper {
    suspend fun getAllFacts(): List<Facts>
    suspend fun insertAll(facts: List<Facts>)
    suspend fun deleteAll()
}