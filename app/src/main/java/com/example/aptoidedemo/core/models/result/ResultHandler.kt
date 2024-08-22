package com.example.aptoidedemo.core.models.result

sealed class ResultHandler<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultHandler<T>()
    data class Error(val exception: Exception) : ResultHandler<Nothing>()
}