package com.example.firstkotlinproject.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstkotlinproject.data.MovieRepository
import com.example.firstkotlinproject.model.Movie
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val _loadingMovieDetailLiveData = MutableLiveData<Movie?>(null)
    val loadingMovieDetailLiveData : LiveData<Movie?> = _loadingMovieDetailLiveData

    private fun loadMovie(movieId: Int){
       viewModelScope.launch {
           _loadingMovieDetailLiveData.value = movieRepository.loadMovie(movieId)
       }
    }
}