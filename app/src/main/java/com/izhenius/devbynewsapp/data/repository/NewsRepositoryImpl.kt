package com.izhenius.devbynewsapp.data.repository

import com.izhenius.devbynewsapp.data.mapper.Mapper
import com.izhenius.devbynewsapp.data.network.safeNetworkCall
import com.izhenius.devbynewsapp.data.network.web.NewsWebService
import com.izhenius.devbynewsapp.data.network.web.model.NewsWebServiceData
import com.izhenius.devbynewsapp.domain.error.ErrorHandler
import com.izhenius.devbynewsapp.domain.model.NewsArticle
import com.izhenius.devbynewsapp.domain.model.Result
import com.izhenius.devbynewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers

class NewsRepositoryImpl(
    private val newsWebService: NewsWebService,
    private val resultMapper: Mapper<NewsWebServiceData, Result<List<NewsArticle>>>,
    private val errorHandler: ErrorHandler
) : NewsRepository {
    override suspend fun getListOfNews(): Result<List<NewsArticle>> {
        return safeNetworkCall(
            Dispatchers.IO,
            { newsWebService.getListOfNews() },
            resultMapper,
            errorHandler
        )
    }
}
