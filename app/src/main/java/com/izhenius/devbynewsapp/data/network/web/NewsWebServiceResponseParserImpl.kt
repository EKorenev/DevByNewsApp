package com.izhenius.devbynewsapp.data.network.web

import com.izhenius.devbynewsapp.data.network.web.model.WebNewsArticle
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class NewsWebServiceResponseParserImpl(
    private val baseUrl: String
) : NewsWebServiceResponseParser {
    override fun parseToWebNewsArticleList(input: String): List<WebNewsArticle> {
        val webNewsArticleList = mutableListOf<WebNewsArticle>()
        val document = Jsoup.parse(input)
        val elements = document.select(NEWS_ITEM_QUERY)
        for (element: Element in elements) {
            val webNewsArticle = parseElementToWebNewsArticle(element)
            webNewsArticleList.add(webNewsArticle)
        }
        return webNewsArticleList
    }

    private fun parseElementToWebNewsArticle(element: Element): WebNewsArticle {
        val url = parseElementToNewsUrl(element)
        val title = parseElementToNewsTitle(element)
        val urlToImage = parseElementToNewsUrlToImage(element)
        return WebNewsArticle(url, title, urlToImage)
    }

    private fun parseElementToNewsUrl(element: Element): String {
        return element
            .attr(NEWS_URL_ATTRIBUTE_KEY)
    }

    private fun parseElementToNewsTitle(element: Element): String {
        return element
            .selectFirst(NEWS_CARD_INFO_QUERY)
            .selectFirst(NEWS_CARD_BODY_QUERY)
            .selectFirst(NEWS_CARD_LINK_QUERY)
            .selectFirst(NEWS_TITLE_QUERY)
            .text()
    }

    private fun parseElementToNewsUrlToImage(element: Element): String {
        return baseUrl.plus(
            element
                .selectFirst(NEWS_URL_TO_IMAGE_QUERY)
                .attr(URL_TO_IMAGE_ATTRIBUTE_KEY)
        )
    }

    companion object {
        const val NEWS_ITEM_QUERY = "div[class=card card_media][data-vr-contentbox^=news]"
        const val NEWS_URL_ATTRIBUTE_KEY = "data-vr-contentbox-url"
        const val NEWS_CARD_INFO_QUERY = "div[class=card__info]"
        const val NEWS_CARD_BODY_QUERY = "div[class=card__body]"
        const val NEWS_CARD_LINK_QUERY = "a[class=card__link]"
        const val NEWS_TITLE_QUERY = "span"
        const val NEWS_URL_TO_IMAGE_QUERY = "img[class=card__img]"
        const val URL_TO_IMAGE_ATTRIBUTE_KEY = "src"
    }
}
