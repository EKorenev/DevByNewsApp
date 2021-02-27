package com.izhenius.devbynewsapp.domain.usecase

import com.izhenius.devbynewsapp.domain.model.NewsArticle
import com.izhenius.devbynewsapp.domain.model.Result

interface GetNewsArticlesUseCase {
    suspend operator fun invoke(): Result<List<NewsArticle>>
}
