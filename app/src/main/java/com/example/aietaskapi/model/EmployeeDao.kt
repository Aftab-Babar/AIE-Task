package com.example.aietaskapi.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(note :List<Employee>)

    @Query("SELECT * FROM userDB")
    fun getAllItems(): List<Employee>
}