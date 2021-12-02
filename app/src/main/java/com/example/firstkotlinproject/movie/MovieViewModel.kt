package com.example.firstkotlinproject.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstkotlinproject.data.MovieRepository
import com.example.firstkotlinproject.model.Movie
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRep: MovieRepository): ViewModel() {

    private val _loadingMovieLiveData = MutableLiveData<List<Movie>>(emptyList())
    val loadingMovieLiveData : LiveData<List<Movie>> = _loadingMovieLiveData


    init {
        loadMovies()
    }

    private fun loadMovies(){
        viewModelScope.launch {
            _loadingMovieLiveData.value = movieRep.loadMovies()
        }
    }

}