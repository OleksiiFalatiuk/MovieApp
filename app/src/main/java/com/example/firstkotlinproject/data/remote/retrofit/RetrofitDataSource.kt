package com.example.firstkotlinproject.data.remote.retrofit

import com.example.firstkotlinproject.data.remote.RemoteDataSource
import com.example.firstkotlinproject.data.remote.retrofit.response.ImageResponse
import com.example.firstkotlinproject.model.Actor
import com.example.firstkotlinproject.model.Genre
import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.model.MovieDetails

class RetrofitDataSource(private val api: MovieApiService): RemoteDataSource {

    companion object{
        const val DEFAULT_SIZE = "original"
    }

    private var imageResponse: ImageResponse? = null
    private var baseUrl: String? = null
    private var posterSize: String? = null
    private var backdropSize: String? = null
    private var profileSize: String? = null


    override suspend fun loadMovies(): List<Movie> {
        sendConfiguration()
        val genre = api.loadGenres().genres
        return api.getTopRated(page = 1).results.map {movie ->
            Movie(
                id = movie.id,
                years = if (movie.adult) 16 else 13,
                name = movie.title,
                time = 120,
                review = movie.voteCount,
                isLiked = false,
                rating = movie.popularity.toInt(),
                avatar = formingImage(baseUrl,posterSize,movie.posterPath),
                genre = genre
                    .filter { genreResponse ->
                    movie.genreId.contains(genreResponse.idGenre)
                }
                    .map { genre->
                        Genre(
                          genre.idGenre,
                          genre.nameGenre
                        )
                    }
            )
        }
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        sendConfiguration()
        val details = api.getDetails(movieId)
        return MovieDetails(
            id = details.id,
            years = if (details.adult) 16 else 13,
            name = details.originalTitle,
            review = details.runtime.toInt(),
            isLiked = false,
            rating = details.popularity.toInt(),
            storyLine = details.overview,
            detailImageRes = formingImage(baseUrl,backdropSize,details.backdropPath),
            genre = details.genres.map { genre ->
                Genre(
                    genre.idGenre,
                    genre.nameGenre
                )
            },
//            actors = listOf(
//                Actor(
//                    id = actors.id,
//                    name = actors.name,
//                    imageRes = formingImage(baseUrl,profileSize,actors.profilePath)
//                )
//            )
        actors = api.loadMovieCredits(movieId).casts.map { actors ->
            Actor(
                    id = actors.id,
                    name = actors.name,
                    imageRes = formingImage(baseUrl,profileSize,actors.profilePath)
                )
        }
        )
    }

    private suspend fun sendConfiguration(){
        if (imageResponse == null){
            imageResponse = api.getConfiguration().images

            baseUrl = imageResponse?.secureBaseUrl
            posterSize = imageResponse?.posterSizes?.find { it == "w500" }
            backdropSize = imageResponse?.backdropSizes?.find { it == "780" }
            profileSize = imageResponse?.profileSizes?.find { it == "w185" }
        }
    }

    private fun formingImage(url: String?, size: String?, path: String?): String? {
        return if (url == null || path == null) {
            null
        } else {
            url.plus(size.takeUnless { it.isNullOrEmpty() } ?: DEFAULT_SIZE).plus(path)
        }
    }

}