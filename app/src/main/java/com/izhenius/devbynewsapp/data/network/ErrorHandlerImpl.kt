package com.izhenius.devbynewsapp.data.network

import com.izhenius.devbynewsapp.domain.error.ErrorEntity
import com.izhenius.devbynewsapp.domain.error.ErrorHandler
import java.io.IOException

class ErrorHandlerImpl : ErrorHandler {
    override fun getError(throwable: Throwable): ErrorEntity {
        val errorMessage: String = throwable.message ?: "Unknown"
        return when (throwable) {
            is IOException -> ErrorEntity.Unknown(errorMessage) // TODO(Need more logic error)
            else -> ErrorEntity.Unknown(errorMessage)
        }
    }
}
