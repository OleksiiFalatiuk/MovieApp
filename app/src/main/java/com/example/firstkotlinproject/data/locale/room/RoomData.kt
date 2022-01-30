package com.example.firstkotlinproject.data.locale.room

import com.example.firstkotlinproject.data.locale.LocaleDataSource
import com.example.firstkotlinproject.model.Actor
import com.example.firstkotlinproject.model.Genre
import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.model.MovieDetails

class RoomData(private val appDb: AppDataBase) : LocaleDataSource {


    override suspend fun loadMovies(): List<Movie> {
        return appDb.getMoviesDao().getMovies().map { info ->
            Movie(
                id = info.movie.id,
                years = info.movie.years,
                name = info.movie.name,
                genre = info.genres.map { genreInfo ->
                    Genre(
                        id = genreInfo.detailsId,
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


    private fun insertOneMove(movie: Movie) {
        val movieBase = MovieDbEntity(
            id = movie.id,
            years = movie.years,
            name = movie.name,
            time = movie.time,
            review = movie.review,
            isLiked = movie.isLiked,
            rating = movie.rating,
            avatar = movie.avatar
        )
        appDb.getMoviesDao().insertMovies(movieBase)

        val genreBase = movie.genre.map {
            Genre(
                name = it.name,
                id = it.id
            )
        }
        insertGenres(genreBase, movieBase.id)

    }

    override fun insertGenres(genreFromApi: List<Genre>, movieId: Int) {
        val genreBase = genreFromApi.map { genre ->
            GenreDbEntity(
                name = genre.name,
                detailsId = movieId
            )
        }

        appDb.getMoviesDao().deleteOldGenreFromFilms(movieId)
        appDb.getMoviesDao().insertGenres(genreBase)


    }

    override fun insertMovies(list: List<Movie>) {
        for (i in list) {
            insertOneMove(i)
        }
    }


    override suspend fun loadMovie(movieId: Int): List<MovieDetails> {
        return appDb.getMovieDetailsDao().getMovieDetails().map { detail ->
            MovieDetails(
                id = detail.movieDetails.detailsId,
                years = detail.movieDetails.years,
                name = detail.movieDetails.name,
                genre = detail.genre.map { genre ->
                    Genre(
                        id = genre.detailsId,
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
                        id = actor.detailsId,
                        name = actor.name,
                        imageRes = actor.imageRes
                    )
                }
            )
        }
    }


    override fun insertDetailsWithActorAndGenre(detailsAPI: MovieDetails){
        val detailsOfMMovie = MovieDetailsDbEntity(
            detailsId = detailsAPI.id,
            detailImageRes = detailsAPI.detailImageRes,
            isLiked = detailsAPI.isLiked,
            name = detailsAPI.name,
            rating = detailsAPI.rating,
            review = detailsAPI.review,
            storyLine = detailsAPI.storyLine,
            years = detailsAPI.years
        )

        appDb.getMovieDetailsDao().insertMovieDetailsSecondOne(detailsOfMMovie)

        val actorsList = detailsAPI.actors.map {
            Actor(
                id = it.id,
                imageRes = it.imageRes,
                name = it.name
            )
        }
        insertActors(actorsList,detailsOfMMovie.detailsId)
    }

    private fun insertActors(actorFromApi: List<Actor>, detailId: Int){
        val actorsInserted = actorFromApi.map {
            ActorDbEntity(
                detailsId = detailId,
                imageRes = it.imageRes,
                name = it.name
            )
        }
        appDb.getMovieDetailsDao().deleteOldActorsFromFilms(detailId)
        appDb.getMovieDetailsDao().insertActorsEntity(actorsInserted)
    }

}