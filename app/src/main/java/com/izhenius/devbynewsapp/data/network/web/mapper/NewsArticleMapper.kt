package com.izhenius.devbynewsapp.data.network.web.mapper

import com.izhenius.devbynewsapp.data.mapper.Mapper
import com.izhenius.devbynewsapp.data.network.web.model.WebNewsArticle
import com.izhenius.devbynewsapp.domain.model.NewsArticle

class NewsArticleMapper : Mapper<WebNewsArticle, NewsArticle> {
    override fun map(input: WebNewsArticle): NewsArticle {
        return NewsArticle(
            input.url,
            input.title.orEmpty(),
            input.urlToImage.orEmpty()
        )
    }
}
