package com.izhenius.devbynewsapp.data.network

import com.izhenius.devbynewsapp.data.mapper.Mapper
import com.izhenius.devbynewsapp.domain.error.ErrorHandler
import com.izhenius.devbynewsapp.domain.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T, R> safeNetworkCall(
    coroutineDispatcher: CoroutineDispatcher,
    networkCall: suspend () -> T,
    resultMapper: Mapper<T, Result<R>>,
    errorHandler: ErrorHandler
): Result<R> {
    return withContext(coroutineDispatcher) {
        try {
            resultMapper.map(networkCall())
        } catch (throwable: Throwable) {
            Result.Failure(errorHandler.getError(throwable))
        }
    }
}
