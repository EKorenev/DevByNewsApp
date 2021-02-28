package com.izhenius.devbynewsapp.data.network.web.mapper

import com.izhenius.devbynewsapp.data.mapper.Mapper
import com.izhenius.devbynewsapp.data.network.web.model.NewsWebServiceData
import com.izhenius.devbynewsapp.domain.error.ErrorEntity

class ErrorEntityMapper : Mapper<NewsWebServiceData, ErrorEntity> {
    override fun map(input: NewsWebServiceData): ErrorEntity {
        val errorMessage = "WEB error: Unknown"
        return ErrorEntity.Unknown(errorMessage) // TODO(Need more logic error)
    }
}
