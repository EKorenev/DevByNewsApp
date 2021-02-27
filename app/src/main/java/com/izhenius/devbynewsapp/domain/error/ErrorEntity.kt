package com.izhenius.devbynewsapp.domain.error

sealed class ErrorEntity(message: String?) : Throwable(message) {
    data class Unknown(override val message: String?) : ErrorEntity(message)
}
