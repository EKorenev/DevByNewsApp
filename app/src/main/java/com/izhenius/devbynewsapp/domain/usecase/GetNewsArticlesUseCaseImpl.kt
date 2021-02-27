package com.izhenius.devbynewsapp.domain.usecase

import com.izhenius.devbynewsapp.domain.model.NewsArticle
import com.izhenius.devbynewsapp.domain.model.Result
import com.izhenius.devbynewsapp.domain.repository.NewsRepository

class GetNewsArticlesUseCaseImpl(private val newsRepository: NewsRepository) : GetNewsArticlesUseCase {
    override suspend fun invoke(): Result<List<NewsArticle>> {
        return newsRepository.getListOfNews()
    }
}
