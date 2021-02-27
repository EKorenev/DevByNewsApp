package com.izhenius.devbynewsapp.domain.repository

import com.izhenius.devbynewsapp.domain.model.NewsArticle
import com.izhenius.devbynewsapp.domain.model.Result

interface NewsRepository {
    suspend fun getListOfNews(): Result<List<NewsArticle>>
}
