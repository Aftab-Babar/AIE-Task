package com.example.aietaskapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aietaskapi.repo.DetailsRepository


class DetailsViewModelFactory(private val detailsRepository: DetailsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsViewModel(detailsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}