package com.izhenius.devbynewsapp.presentation.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.izhenius.devbynewsapp.databinding.ActivityMainBinding
import com.izhenius.devbynewsapp.domain.model.NewsArticle
import com.izhenius.devbynewsapp.presentation.view.adapter.NewsArticleAdapter
import com.izhenius.devbynewsapp.presentation.viewModel.NewsArticleViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val newsArticleAdapter = NewsArticleAdapter()
    private val newsArticleViewModel by viewModels<NewsArticleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.apply {
            adapter = newsArticleAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        val newsArticlesObserver = Observer<List<NewsArticle>> {
            it ?: return@Observer
            newsArticleAdapter.addItems(it)
        }

        newsArticleViewModel.articles.observe(this, newsArticlesObserver)
    }
}
