package com.example.firstkotlinproject.data.remote.retrofit

import com.example.firstkotlinproject.data.remote.retrofit.response.ConfigurationResponse
import com.example.firstkotlinproject.data.remote.retrofit.response.TopRatedResponse
import retrofit2.http.GET

interface MovieApiService {

    @GET("configuration")
    suspend fun getConfiguration(): ConfigurationResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(): TopRatedResponse
}