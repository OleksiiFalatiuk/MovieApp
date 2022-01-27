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

    override fun insertGenres(genreFromApi: List<Movie>) {


        val genreApp = genreFromApi.find { genreFromApi == it.genre }?.genre
        val ggg = genreApp?.map { it1 ->
            GenreDbEntity(
                name = it1.name,
                detailsId = it1.id
            )
        }


//        val genreDb = genreFromApi.map { genreA ->
//            GenreDbEntity(
//                name = genreA.name,
//                detailsId = genreA.id
//            )
//        }
//        appDb.getMoviesDao().insertGenres(genreDb)


        appDb.getMoviesDao().insertGenres(ggg)
    }

    override fun insertAll(app: List<Movie>) {
        val everything = app.map { info ->
            MovieWithGenres(
                MovieDbEntity(
                    id = info.id,
                    years = info.years,
                    name = info.name,
                    time = info.time,
                    review = info.review,
                    isLiked = info.isLiked,
                    rating = info.rating,
                    avatar = info.avatar
                ),
                genres = info.genre.map {
                    GenreDbEntity(
                        name = it.name,
                        detailsId = it.id
                    )
                }
            )

        }
        appDb.getMoviesDao().insertAll(everything)
    }


//    override fun insertMoviesWithGenres(movies: List<Movie>, genre: List<Genre>) {
//        val dbMovie = movies.map { movie ->
//            MovieDbEntity(
//                id = movie.id,
//                years = movie.years,
//                name = movie.name,
//                time = movie.time,
//                review = movie.review,
//                isLiked = movie.isLiked,
//                rating = movie.rating,
//                avatar = movie.avatar
//            )
//        }
//        val dbGenre = genre.map { genre ->
//            GenreDbEntity(
//                detailsId = genre.id,
//                name = genre.name
//            )
//        }
//        appDb.getMoviesDao().insertMoviesWithGenres(dbMovie,dbGenre)
//    }


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

//    fun insertActorsDetails(actorDetail: List<Actor>) {
//        val someActors = actorDetail.map { actor ->
//            ActorDbEntity(
//                detailsId = actor.id,
//                name = actor.name,
//                imageRes = actor.imageRes
//            )
//        }
//        appDb.getMovieDetailsDao().insertActors(someActors)
//    }
}