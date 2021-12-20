package com.example.firstkotlinproject.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstkotlinproject.data.MovieRepository
import com.example.firstkotlinproject.data.remote.RemoteDataSource
import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.model.MovieDetails
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val _loadingMovieDetailLiveData = MutableLiveData<MovieDetails?>(null)
    val loadingMovieDetailLiveData : LiveData<MovieDetails?> = _loadingMovieDetailLiveData

    fun loadMovie(movieId: Int){
       viewModelScope.launch {
           _loadingMovieDetailLiveData.value = movieRepository.loadMovie(movieId)
       }
    }
}