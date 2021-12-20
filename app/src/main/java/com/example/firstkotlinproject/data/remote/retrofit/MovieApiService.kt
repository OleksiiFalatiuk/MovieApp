package com.example.firstkotlinproject.data.remote.retrofit

import com.example.firstkotlinproject.data.remote.retrofit.response.*
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("configuration")
    suspend fun getConfiguration(): ConfigurationResponse

    @GET("genre/movie/list")
    suspend fun loadGenres(): GenresResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int
    ): TopRatedResponse

    @GET("movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailsResponse

    @GET("person/{person_id}")
    suspend fun getActors(
        @Path("person_id") personId: Int
    ): ActorsResponse

    @GET("movie/{movie_id}/credits")
    suspend fun loadMovieCredits(
        @Path("movie_id") movieId: Int
    ): MovieCastResponse
}