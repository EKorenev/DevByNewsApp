package com.izhenius.devbynewsapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.izhenius.devbynewsapp.di.GetNewsArticlesUseCaseProvider
import com.izhenius.devbynewsapp.domain.error.ErrorEntity
import com.izhenius.devbynewsapp.domain.model.NewsArticle
import com.izhenius.devbynewsapp.domain.model.Result
import kotlinx.coroutines.launch

class NewsArticleViewModel : ViewModel() {
    val errorEntity = MutableLiveData<ErrorEntity>()
    private val _articles = MutableLiveData<List<NewsArticle>>()
    val articles: LiveData<List<NewsArticle>> get() = _articles
    private val getNewsArticlesUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetNewsArticlesUseCaseProvider.provideUseCase()
    }

    init {
        downloadListOfNews()
    }

    fun downloadListOfNews() {
        viewModelScope.launch {
            val result = getNewsArticlesUseCase()
            if (result is Result.Success) {
                errorEntity.value = null
                _articles.value = result.value
            } else if (result is Result.Failure) {
                errorEntity.value = result.error
                _articles.value = null
            }
        }
    }
}
