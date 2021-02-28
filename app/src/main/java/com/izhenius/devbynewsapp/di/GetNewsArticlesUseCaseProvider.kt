package com.izhenius.devbynewsapp.di

import com.izhenius.devbynewsapp.domain.usecase.GetNewsArticlesUseCaseImpl

object GetNewsArticlesUseCaseProvider {
    fun provideUseCase() = GetNewsArticlesUseCaseImpl(NewsRepositoryProvider.provideRepository())
}
