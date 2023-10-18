package com.example.aietaskapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "userDB")
data class Employee(
    @PrimaryKey()
    val id: Int,
    val address: String,
    val age: Int,
    val contactNumber: String,
    val dob: String,
    val email: String,
    val firstName: String,
     val imageUrl: String,
    val lastName: String,
    val salary: Double
)