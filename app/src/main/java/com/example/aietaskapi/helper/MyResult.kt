package com.example.aietaskapi.helper

sealed class MyResult<out T> {
    data class Success<out T>(val data: T) : MyResult<T>()
    data class Error(val message: String) : MyResult<Nothing>()
    data object Loading : MyResult<Nothing>()
}