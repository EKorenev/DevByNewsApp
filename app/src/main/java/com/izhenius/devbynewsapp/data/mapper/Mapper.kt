package com.izhenius.devbynewsapp.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}
