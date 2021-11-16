package com.example.firstkotlinproject.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firstkotlinproject.data.MovieRepository

class MovieViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = MovieViewModel(repository) as T

}