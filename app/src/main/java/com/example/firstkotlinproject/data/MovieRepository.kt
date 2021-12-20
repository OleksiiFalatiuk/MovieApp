package com.example.firstkotlinproject.data


import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.model.MovieDetails

interface MovieRepository {
    suspend fun loadMovies(): List<Movie>
    suspend fun loadMovie(movieId: Int): MovieDetails?
}
