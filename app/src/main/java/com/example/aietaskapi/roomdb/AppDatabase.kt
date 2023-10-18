package com.example.aietaskapi.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aietaskapi.model.Employee
import com.example.aietaskapi.model.EmployeeDao

@Database(entities = [Employee::class] , version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun detailResponseDao(): EmployeeDao
}