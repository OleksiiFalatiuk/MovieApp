package com.example.firstkotlinproject.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstkotlinproject.data.MovieRepository
import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.result.Result.Error
import com.example.firstkotlinproject.result.Result
import com.example.firstkotlinproject.result.Result.Success
import kotlinx.coroutines.launch


class MovieViewModel(private val movieRep: MovieRepository): ViewModel() {

    private val _loadingMovieLiveData = MutableLiveData<List<Movie>>(emptyList())
    private val _errorMessageForMovieLiveData = MutableLiveData<String>()

    val loadingMovieLiveData : LiveData<List<Movie>> = _loadingMovieLiveData
    val errorMessageForMovieLiveData : LiveData<String> = _errorMessageForMovieLiveData


    init {
        loadMovies()
    }

    private fun loadMovies(){
        viewModelScope.launch {
//            _loadingMovieLiveData.value = movieRep.loadMovies()
            stateResult(movieRep.loadMovies())
        }
    }

    private fun stateResult(state: Result<List<Movie>>){
        when(state){
            is Success -> _loadingMovieLiveData.value = state.data
            is Error -> _errorMessageForMovieLiveData.value = state.message
        }
    }

}