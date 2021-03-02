package com.izhenius.devbynewsapp.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.izhenius.devbynewsapp.databinding.ActivityMainBinding
import com.izhenius.devbynewsapp.domain.error.ErrorEntity
import com.izhenius.devbynewsapp.domain.model.NewsArticle
import com.izhenius.devbynewsapp.presentation.view.adapter.NewsArticleAdapter
import com.izhenius.devbynewsapp.presentation.viewModel.NewsArticleViewModel

class MainActivity : AppCompatActivity(), NewsArticleAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private val newsArticleAdapter = NewsArticleAdapter(this)
    private val newsArticleViewModel by viewModels<NewsArticleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = newsArticleAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        val newsArticlesObserver = Observer<List<NewsArticle>> {
            when {
                it == null -> {
                    binding.imageViewError.visibility = View.VISIBLE
                    return@Observer
                }
                it.isEmpty() -> {
                    binding.imageViewError.visibility = View.VISIBLE
                }
                else -> {
                    binding.imageViewError.visibility = View.INVISIBLE
                }
            }
            newsArticleAdapter.addItems(it)
        }

        newsArticleViewModel.articles.observe(this, newsArticlesObserver)

        val errorObserver = Observer<ErrorEntity> {
            it ?: return@Observer
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }

        newsArticleViewModel.errorEntity.observe(this, errorObserver)
    }

    override fun onItemClick(url: String) {
        startNewsArticleActivity(url)
    }

    private fun startNewsArticleActivity(url: String) {
        val intent = Intent(this, NewsArticleActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }
}
