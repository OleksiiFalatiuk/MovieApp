package com.example.firstkotlinproject.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstkotlinproject.data.MovieRepository
import com.example.firstkotlinproject.model.MovieDetails
import com.example.firstkotlinproject.result.Result
import com.example.firstkotlinproject.result.Success
import com.example.firstkotlinproject.result.Error

import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val _loadingMovieDetailLiveData = MutableLiveData<MovieDetails?>(null)
    private val _errorMessageForMovieDetailsLiveData = MutableLiveData<String>()

    val loadingMovieDetailLiveData : LiveData<MovieDetails?> = _loadingMovieDetailLiveData
    val errorMessageForMovieDetailsLiveData : LiveData<String> = _errorMessageForMovieDetailsLiveData

    fun loadMovie(movieId: Int){
       viewModelScope.launch {
           stateResultDetails(movieRepository.loadMovie(movieId))
       }
    }

    private fun stateResultDetails(state: Result<MovieDetails>){
        when(state){
            is Success -> _loadingMovieDetailLiveData.value = state.data
            is Error -> _errorMessageForMovieDetailsLiveData.value = state.message
        }
    }
}