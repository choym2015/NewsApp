package com.chopas.newsappmvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.chopas.newsappmvvm.R
import com.chopas.newsappmvvm.databinding.ActivityNewsBinding
import com.chopas.newsappmvvm.db.ArticleDatabase
import com.chopas.newsappmvvm.repository.NewsRepository
import com.chopas.newsappmvvm.viewmodel.NewsViewModel
import com.chopas.newsappmvvm.viewmodel.NewsViewModelProviderFactory

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigationView.setupWithNavController(Navigation.findNavController(this, R.id.newsNavHostFragment))

        val repository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]
    }
}