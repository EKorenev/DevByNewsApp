package com.izhenius.devbynewsapp.data.network.web

import com.izhenius.devbynewsapp.data.mapper.Mapper
import com.izhenius.devbynewsapp.data.network.web.model.NewsWebServiceData

class NewsWebServiceImpl(
    private val baseUrl: String,
    private val newsWebServiceConnector: NewsWebServiceConnector,
    private val newsWebServiceDataMapper: Mapper<Any, NewsWebServiceData>
) : NewsWebService {
    override suspend fun getListOfNews(): NewsWebServiceData {
        val urlResource = newsWebServiceConnector.getResource(baseUrl)
        return newsWebServiceDataMapper.map(urlResource)
    }
}
