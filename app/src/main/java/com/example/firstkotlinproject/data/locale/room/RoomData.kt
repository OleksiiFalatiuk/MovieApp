package com.example.firstkotlinproject.data.locale.room

import com.example.firstkotlinproject.data.locale.LocaleDataSource
import com.example.firstkotlinproject.model.Actor
import com.example.firstkotlinproject.model.Genre
import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.model.MovieDetails

class RoomData(private val appDb: AppDataBase): LocaleDataSource {

    override suspend fun loadMovies(): List<Movie> {
        return appDb.getMoviesDao().getMovies().map { info ->
            Movie(
                id = info.movie.id,
                years = info.movie.years,
                name = info.movie.name,
                genre = info.genres.map { genreInfo ->
                    Genre(
                        id = genreInfo.id,
                        name = genreInfo.name
                    )
                },
                time = info.movie.time,
                review = info.movie.review,
                isLiked = info.movie.isLiked,
                rating = info.movie.rating,
                avatar = info.movie.avatar
            )
        }
    }

    override fun insertMovies(movieFromApi: List<Movie>) {
        val db = movieFromApi.map { movie ->
            MovieDbEntity(
                id = movie.id,
                years = movie.years,
                name = movie.name,
                time = movie.time,
                review = movie.review,
                isLiked = movie.isLiked,
                rating = movie.rating,
                avatar = movie.avatar
            )
        }
        appDb.getMoviesDao().insertMovies(db)
    }

    override suspend fun loadMovie(movieId: Int): List<MovieDetails> {
        return appDb.getMovieDetailsDao().getMovieDetails().map { detail ->
            MovieDetails(
                id = detail.movieDetails.detailsId,
                years = detail.movieDetails.years,
                name = detail.movieDetails.name,
                genre = detail.genre.map { genre ->
                    Genre(
                        id = genre.id,
                        name = genre.name
                    )
                },
                review = detail.movieDetails.review,
                isLiked = detail.movieDetails.isLiked,
                rating = detail.movieDetails.rating,
                detailImageRes = detail.movieDetails.detailImageRes,
                storyLine = detail.movieDetails.storyLine,
                actors = detail.actors.map { actor ->
                    Actor(
                        id = actor.id,
                        name = actor.name,
                        imageRes = actor.imageRes
                    )
                }
            )
        }
    }

    override fun insertMovieDetails(detailsFromApi: List<MovieDetails>) {
        val dbDetails = detailsFromApi.map { details ->
            MovieDetailsDbEntity(
                detailsId = details.id,
                years = details.years,
                name = details.name,
                review = details.review,
                isLiked = details.isLiked,
                rating = details.rating,
                detailImageRes = details.detailImageRes,
                storyLine = details.storyLine
            )
        }
        appDb.getMovieDetailsDao().insertMovieDetails(dbDetails)
    }
}