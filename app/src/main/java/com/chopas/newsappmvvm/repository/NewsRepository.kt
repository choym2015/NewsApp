package com.chopas.newsappmvvm.repository

import androidx.lifecycle.LiveData
import com.chopas.newsappmvvm.api.RetrofitInstance
import com.chopas.newsappmvvm.db.ArticleDatabase
import com.chopas.newsappmvvm.model.Article
import com.chopas.newsappmvvm.model.NewsResponse
import com.chopas.newsappmvvm.util.Resource
import retrofit2.Response

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse> {
        return RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
    }

    suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse> {
        return RetrofitInstance.api.searchNews(searchQuery, pageNumber)
    }

    suspend fun upsert(article: Article) {
        db.getArticleDao().upsert(article)
    }

    fun getSavedArticles(): LiveData<List<Article>> {
        return db.getArticleDao().getAllArticles()
    }

    suspend fun deleteArticle(article: Article) {
        db.getArticleDao().deleteArticle(article)
    }
}