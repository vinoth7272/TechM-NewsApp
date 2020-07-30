package com.example.techmnewsapp.data.local

import com.example.techmnewsapp.data.model.Facts

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getAllFacts() = appDatabase.getFactsDao().getAll()

    override suspend fun insertAll(facts: List<Facts>) = appDatabase.getFactsDao().insertAll(facts)
    override suspend fun deleteAll() = appDatabase.getFactsDao().deleteAll()
}