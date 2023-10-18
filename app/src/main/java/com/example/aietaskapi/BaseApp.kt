package com.example.aietaskapi

import android.app.Application
import androidx.room.Room
import com.example.aietaskapi.helper.AllConstants
import com.example.aietaskapi.roomdb.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp:Application() {
    companion object {
       lateinit var database: AppDatabase
    }
    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "user-db"
        ).build()
    }
}