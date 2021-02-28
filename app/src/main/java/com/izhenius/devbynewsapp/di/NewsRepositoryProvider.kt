package com.izhenius.devbynewsapp.di

import com.izhenius.devbynewsapp.data.mapper.ListMapperImpl
import com.izhenius.devbynewsapp.data.network.ErrorHandlerImpl
import com.izhenius.devbynewsapp.data.network.web.mapper.ErrorEntityMapper
import com.izhenius.devbynewsapp.data.network.web.mapper.NewsArticleMapper
import com.izhenius.devbynewsapp.data.network.web.mapper.ResultMapper
import com.izhenius.devbynewsapp.data.repository.NewsRepositoryImpl
import com.izhenius.devbynewsapp.domain.repository.NewsRepository
import com.izhenius.devbynewsapp.utils.AppConstants

object NewsRepositoryProvider {
    fun provideRepository(): NewsRepository {
        return NewsRepositoryImpl(
            NewsWebServiceProvider.provideWebService(
                AppConstants.NEWS_WEB_SERVICE_BASE_URL,
                NewsWebServiceProvider.provideWebServiceConnector(),
                NewsWebServiceProvider.provideWebServiceResponseParser(AppConstants.NEWS_WEB_SERVICE_BASE_URL)
            ),
            ResultMapper(
                ListMapperImpl(NewsArticleMapper()),
                ErrorEntityMapper()
            ),
            ErrorHandlerImpl()
        )
    }
}
