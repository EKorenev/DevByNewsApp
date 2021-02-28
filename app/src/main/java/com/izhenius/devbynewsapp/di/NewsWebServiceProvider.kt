package com.izhenius.devbynewsapp.di

import com.izhenius.devbynewsapp.data.network.web.NewsWebService
import com.izhenius.devbynewsapp.data.network.web.NewsWebServiceConnector
import com.izhenius.devbynewsapp.data.network.web.NewsWebServiceConnectorImpl
import com.izhenius.devbynewsapp.data.network.web.NewsWebServiceImpl
import com.izhenius.devbynewsapp.data.network.web.NewsWebServiceResponseParser
import com.izhenius.devbynewsapp.data.network.web.NewsWebServiceResponseParserImpl
import com.izhenius.devbynewsapp.data.network.web.mapper.NewsWebServiceDataMapper

object NewsWebServiceProvider {
    @Volatile
    private var webService: NewsWebService? = null

    fun provideWebService(
        baseUrl: String,
        newsWebServiceConnector: NewsWebServiceConnector,
        newsWebServiceResponseParser: NewsWebServiceResponseParser
    ): NewsWebService {
        return webService ?: synchronized(this) {
            webService ?: NewsWebServiceImpl(
                baseUrl,
                newsWebServiceConnector,
                NewsWebServiceDataMapper(
                    newsWebServiceResponseParser
                )
            )
        }
    }

    fun provideWebServiceConnector() = NewsWebServiceConnectorImpl()

    fun provideWebServiceResponseParser(baseUrl: String) =
        NewsWebServiceResponseParserImpl(baseUrl)
}
