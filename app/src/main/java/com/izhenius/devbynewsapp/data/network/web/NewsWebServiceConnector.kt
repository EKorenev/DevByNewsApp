package com.izhenius.devbynewsapp.data.network.web

interface NewsWebServiceConnector {
    suspend fun getResource(url: String): Any
}
