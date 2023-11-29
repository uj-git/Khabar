package com.umang.newsapp.domain.usecases.news

import com.umang.newsapp.data.local.NewsDao
import com.umang.newsapp.domain.model.Article
import com.umang.newsapp.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }
}