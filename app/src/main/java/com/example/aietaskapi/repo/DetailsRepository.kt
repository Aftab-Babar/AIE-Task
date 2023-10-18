package com.example.aietaskapi.repo

import android.util.Log
import com.example.aietaskapi.helper.MyResult
import com.example.aietaskapi.model.Employee
import com.example.aietaskapi.model.EmployeeDao
import com.example.aietaskapi.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DetailsRepository @Inject constructor(private val apiService: ApiService, private val detailResponseDao: EmployeeDao) {
     suspend fun fetchSave(): MyResult<List<Employee>> {
        try {
            val response = apiService.login()

            return if (response.isSuccessful) {
                val detailResponseList = response.body() ?: emptyList()
                if (detailResponseList.isNotEmpty()) {
                    runBlocking {
                        launch(Dispatchers.IO){
                             detailResponseDao.insert(detailResponseList)
                        }

                    }
                    MyResult.Success(detailResponseList)
                } else {
                    MyResult.Error("Response body is empty")
                }
            } else {
                MyResult.Error("Login failed")
            }
        }
        catch (e: Exception) {
             return if (e.message!!.contains("Unable to resolve host")){
                MyResult.Error("No Internet connection")
            }else{
                MyResult.Error("${e.message} Exception")
            }

        }
    }

      fun getDetailsFromDatabase(): List<Employee>? {
          var list:List<Employee> = ArrayList()
          runBlocking {
              launch(Dispatchers.IO) {
                list=  detailResponseDao.getAllItems()
              }
          }
          return list

    }
}