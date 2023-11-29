package com.umang.newsapp.presentation.bookmark

import com.umang.newsapp.domain.model.Article
import com.umang.newsapp.domain.usecases.news.SelectArticles

data class BookmarkState(
    val articles: List<Article> = emptyList()
)