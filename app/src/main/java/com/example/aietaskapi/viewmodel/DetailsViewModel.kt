package com.example.aietaskapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.aietaskapi.helper.MyResult
import com.example.aietaskapi.model.Employee
import com.example.aietaskapi.repo.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel

class DetailsViewModel @Inject constructor(private val detailsRepository: DetailsRepository) :
    ViewModel() {
    val loginResult: LiveData<MyResult<List<Employee>>>
        get() = liveData {
            val result = detailsRepository.fetchSave()
            emit(result)
        }

    val detailsFromDatabase: LiveData<List<Employee>>
        get() = liveData {
            val details = detailsRepository.getDetailsFromDatabase()
            details?.let { emit(it) }
        }

}





