package com.umang.newsapp.domain.usecases.news

import com.umang.newsapp.data.local.NewsDao
import com.umang.newsapp.domain.model.Article
import com.umang.newsapp.domain.repository.NewsRepository

class SelectArticle (
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String):Article?{
        return newsRepository.selectArticle(url)
    }
}