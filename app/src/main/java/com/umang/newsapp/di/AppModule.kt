package com.umang.newsapp.di

import android.app.Application
import androidx.room.Room
import com.umang.newsapp.data.local.NewsDao
import com.umang.newsapp.data.local.NewsDatabase
import com.umang.newsapp.data.local.NewsTypeConverter
import com.umang.newsapp.data.manager.LocalUserManagerImpl
import com.umang.newsapp.data.remote.NewsApi
import com.umang.newsapp.data.repository.NewsRepositoryImpl
import com.umang.newsapp.domain.manager.LocalUserManager
import com.umang.newsapp.domain.repository.NewsRepository
import com.umang.newsapp.domain.usecases.appentry.AppEntryUseCases
import com.umang.newsapp.domain.usecases.appentry.ReadAppEntry
import com.umang.newsapp.domain.usecases.appentry.SaveAppEntry
import com.umang.newsapp.domain.usecases.news.DeleteArticle
import com.umang.newsapp.domain.usecases.news.GetNews
import com.umang.newsapp.domain.usecases.news.NewsUseCases
import com.umang.newsapp.domain.usecases.news.SearchNews
import com.umang.newsapp.domain.usecases.news.SelectArticle
import com.umang.newsapp.domain.usecases.news.SelectArticles
import com.umang.newsapp.domain.usecases.news.UpsertArticle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import com.umang.newsapp.util.Constants.BASE_URL
import com.umang.newsapp.util.Constants.News_DATABASE_NAME
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) : AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ) : NewsRepository = NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ) : NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ) : NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = News_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ) : NewsDao = newsDatabase.newsDao
}