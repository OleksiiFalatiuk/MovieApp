package com.example.firstkotlinproject.data.remote

import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.model.MovieDetails

interface RemoteDataSource {
    suspend fun loadMovies(movieId: Int): List<Movie>
    suspend fun loadMovie(movieId: Int, personId: Int): MovieDetails
}