package com.izhenius.devbynewsapp.domain.error

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}
