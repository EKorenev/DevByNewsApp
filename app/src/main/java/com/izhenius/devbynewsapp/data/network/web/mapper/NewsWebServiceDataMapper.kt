package com.izhenius.devbynewsapp.data.network.web.mapper

import com.izhenius.devbynewsapp.data.mapper.Mapper
import com.izhenius.devbynewsapp.data.network.web.NewsWebServiceResponseParser
import com.izhenius.devbynewsapp.data.network.web.model.NewsWebServiceData

class NewsWebServiceDataMapper(
    private val newsWebServiceResponseParser: NewsWebServiceResponseParser
) : Mapper<Any, NewsWebServiceData> {
    override fun map(input: Any): NewsWebServiceData {
        val webNewsArticles = when {
            input is String && input.isNotEmpty() -> newsWebServiceResponseParser.parseToWebNewsArticleList(input)
            else -> (null)
        }
        return NewsWebServiceData(webNewsArticles)
    }
}
