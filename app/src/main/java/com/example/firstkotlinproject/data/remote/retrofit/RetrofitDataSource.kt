package com.example.firstkotlinproject.data.remote.retrofit

import com.example.firstkotlinproject.data.remote.RemoteDataSource
import com.example.firstkotlinproject.data.remote.retrofit.response.ImageResponse
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
        TODO("Not yet implemented")
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        TODO("Not yet implemented")
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

    private fun formingImage(url: String?, size: String?, path: String?){
        if (url == null || path == null){
            null
        }else{
            url.plus(size.takeUnless { it.isNullOrEmpty() }?: DEFAULT_SIZE).plus(path)
        }
    }

}