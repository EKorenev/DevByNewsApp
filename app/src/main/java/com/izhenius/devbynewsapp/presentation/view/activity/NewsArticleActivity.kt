package com.izhenius.devbynewsapp.presentation.view.activity

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.izhenius.devbynewsapp.databinding.ActivityNewsArticleBinding

class NewsArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("url").orEmpty()

        val webView = binding.webView
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
        setWebViewDefaultSettings(webView)
        webView.loadUrl(url)
    }

    private fun setWebViewDefaultSettings(webView: WebView) {
        val webViewSettings = webView.settings
        webViewSettings.useWideViewPort = true
        webViewSettings.loadWithOverviewMode = true
        webViewSettings.builtInZoomControls = true
    }
}
