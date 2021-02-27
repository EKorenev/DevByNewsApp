package com.izhenius.devbynewsapp.domain.model

import com.izhenius.devbynewsapp.domain.error.ErrorEntity

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(val error: ErrorEntity) : Result<Nothing>()
}
