package com.example.techmnewsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.techmnewsapp.data.local.dao.FactsDao
import com.example.techmnewsapp.data.model.Facts

@Database(entities = [Facts::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFactsDao(): FactsDao

}