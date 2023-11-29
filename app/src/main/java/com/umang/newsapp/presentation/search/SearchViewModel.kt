package com.umang.newsapp.presentation.search

import android.view.View
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.umang.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
     val state: State<SearchState> = _state


    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.UpdateSearchQuery ->{
                _state.value = state.value.copy(searchQuery = event.searchQuery)
            }
            is SearchEvent.SearchNews ->{
                searchNews()
            }
        }
    }

    private fun searchNews(){
        val articles = newsUseCases.searchNews(
            searchQuery = state.value.searchQuery,
            sources = listOf()
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles)
    }

}