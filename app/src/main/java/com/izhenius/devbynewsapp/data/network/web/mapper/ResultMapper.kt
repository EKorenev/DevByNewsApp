package com.izhenius.devbynewsapp.data.network.web.mapper

import com.izhenius.devbynewsapp.data.mapper.ListMapper
import com.izhenius.devbynewsapp.data.mapper.Mapper
import com.izhenius.devbynewsapp.data.network.web.model.NewsWebServiceData
import com.izhenius.devbynewsapp.data.network.web.model.WebNewsArticle
import com.izhenius.devbynewsapp.domain.error.ErrorEntity
import com.izhenius.devbynewsapp.domain.model.NewsArticle
import com.izhenius.devbynewsapp.domain.model.Result

class ResultMapper(
    private val newsArticleListMapper: ListMapper<WebNewsArticle, NewsArticle>,
    private val errorEntityMapper: Mapper<NewsWebServiceData, ErrorEntity>
) :
    Mapper<NewsWebServiceData, Result<List<NewsArticle>>> {
    override fun map(input: NewsWebServiceData): Result<List<NewsArticle>> {
        return when (input.articles) {
            null -> Result.Failure(errorEntityMapper.map(input))
            else -> Result.Success(newsArticleListMapper.map(input.articles))
        }
    }
}
