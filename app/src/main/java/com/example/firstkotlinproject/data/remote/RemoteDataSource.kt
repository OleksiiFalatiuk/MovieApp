package com.example.firstkotlinproject.data.remote

import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.model.MovieDetails

interface RemoteDataSource {
    suspend fun loadMovies(): List<Movie>
    suspend fun loadMovie(movieId: Int): MovieDetails
}