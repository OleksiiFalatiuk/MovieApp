package com.example.firstkotlinproject.data.locale

import com.example.firstkotlinproject.data.locale.room.GenreDbEntity
import com.example.firstkotlinproject.data.locale.room.MovieDbEntity
import com.example.firstkotlinproject.model.Actor
import com.example.firstkotlinproject.model.Genre
import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.model.MovieDetails

interface LocaleDataSource {

    suspend fun loadMovies(): List<Movie>

    fun insertMovies(list: List<Movie>)

    suspend fun loadMovie(movieId: Int): List<MovieDetails>

    fun insertDetailsWithActorAndGenre(detailsAPI: MovieDetails)

    fun insertGenres(genreFromApi: List<Genre>, movieId: Int)
}