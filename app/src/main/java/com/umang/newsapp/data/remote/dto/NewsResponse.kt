package com.umang.newsapp.data.remote.dto

import com.umang.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)