package com.example.firstkotlinproject.data.locale.room

import com.example.firstkotlinproject.data.locale.LocaleDataSource
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
                isLiked = false,
                rating = info.movie.rating,
                avatar = info.movie.avatar
            )
        }
    }

    override fun insertMovies(movieFromApi: List<Movie>) {
        TODO("Not yet implemented")
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        TODO("Not yet implemented")
    }

    override fun insertMovieDetails(detailsFromApi: List<MovieDetails>) {
        TODO("Not yet implemented")
    }
}