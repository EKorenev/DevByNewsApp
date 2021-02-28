package com.izhenius.devbynewsapp.data.network.web

import com.izhenius.devbynewsapp.data.network.web.model.NewsWebServiceData

interface NewsWebService {
    suspend fun getListOfNews(): NewsWebServiceData
}
