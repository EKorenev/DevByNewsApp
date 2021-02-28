package com.izhenius.devbynewsapp.data.network.web

import com.izhenius.devbynewsapp.data.network.web.model.WebNewsArticle

interface NewsWebServiceResponseParser {
    fun parseToWebNewsArticleList(input: String): List<WebNewsArticle>
}
