package com.izhenius.devbynewsapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.izhenius.devbynewsapp.databinding.LayoutNewsArticleItemBinding
import com.izhenius.devbynewsapp.domain.model.NewsArticle

class NewsArticleAdapter : RecyclerView.Adapter<NewsArticleViewHolder>() {
    private val items = mutableListOf<NewsArticle>()
    private lateinit var binding: LayoutNewsArticleItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        binding = LayoutNewsArticleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        val newsArticle = items[position]
        val newsArticleTitle = newsArticle.title.orEmpty()
        val newsArticleUrlToImage = newsArticle.urlToImage.orEmpty()
        holder.bind(newsArticleTitle, newsArticleUrlToImage)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(newItems: List<NewsArticle>) {
        if (newItems.isNotEmpty()) {
            items.addAll(newItems)
            val newItemsCount = newItems.size
            val newItemsPositionStart = items.lastIndex - newItemsCount
            notifyItemRangeInserted(newItemsPositionStart, newItemsCount)
        }
    }
}

class NewsArticleViewHolder(layoutItemBinding: LayoutNewsArticleItemBinding) :
    RecyclerView.ViewHolder(layoutItemBinding.root) {
    private val imageNewsArticle = layoutItemBinding.imageNewsArticle
    private val newsArticleTitle = layoutItemBinding.textViewNewsArticleTitle

    fun bind(title: String, urlToImage: String) {
        newsArticleTitle.text = title
        imageNewsArticle.load(urlToImage)
    }
}
