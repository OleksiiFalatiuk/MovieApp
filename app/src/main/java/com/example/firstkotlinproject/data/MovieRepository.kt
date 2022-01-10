package com.example.firstkotlinproject.data


import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.model.MovieDetails
import com.example.firstkotlinproject.result.Result

interface MovieRepository {
    suspend fun loadMovies(): Result<List<Movie>>
    suspend fun loadMovie(movieId: Int): Result<MovieDetails>
}
