package com.example.aietaskapi.network

import com.example.aietaskapi.model.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("employee?noofRecords=50&idStarts=1001")
    suspend fun login(): Response<EmployeeResponse>


}