package com.izhenius.devbynewsapp.data.network.web

import org.jsoup.Jsoup
import java.io.IOException

class NewsWebServiceConnectorImpl : NewsWebServiceConnector {
    override suspend fun getResource(url: String): Any {
        return try {
            val document = Jsoup.connect(url).get()
            document.html()
        } catch (e: IOException) {
            ""
        }
    }
}
