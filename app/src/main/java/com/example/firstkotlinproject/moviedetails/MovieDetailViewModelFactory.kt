package com.example.firstkotlinproject.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firstkotlinproject.data.MovieRepository

class MovieDetailViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = MovieDetailViewModel(repository) as T
}