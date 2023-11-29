package com.umang.newsapp.domain.usecases.news

import com.umang.newsapp.data.local.NewsDao
import com.umang.newsapp.domain.model.Article
import com.umang.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsRepository: NewsRepository
) {

    operator fun invoke() : Flow<List<Article>>{
        return newsRepository.selectArticles()
    }
}