package com.umang.newsapp.data.remote

import com.umang.newsapp.data.remote.dto.NewsResponse
import com.umang.newsapp.util.Constants.API_KEY
import com.umang.newsapp.util.Constants.COUNTRY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("page") page : Int,
        @Query("sources") sources : String,
        @Query("country") country : String = COUNTRY,
        @Query("apiKey") apiKey : String = API_KEY
    ) : NewsResponse

    @GET("top-headlines")
    suspend fun searchNews(
        @Query("q") searchQuery : String,
        @Query("page") page : Int,
        @Query("sources") sources : String,
        @Query("country") country : String = COUNTRY,
        @Query("apiKey") apiKey : String = API_KEY
    ) : NewsResponse
}