package com.example.firstkotlinproject.data

import com.example.firstkotlinproject.data.remote.RemoteDataSource
import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.model.MovieDetails

class MovieRepositoryImpl(
    private val remoteDataResource: RemoteDataSource
    ): MovieRepository  {
    override suspend fun loadMovies(): List<Movie> {
        return remoteDataResource.loadMovies()
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails? {
        return remoteDataResource.loadMovie(movieId)
    }
}