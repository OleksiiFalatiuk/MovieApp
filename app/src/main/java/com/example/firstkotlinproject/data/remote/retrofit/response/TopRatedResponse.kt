package com.example.firstkotlinproject.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TopRatedResponse(
    @SerialName("page") val page: Long,
    @SerialName("results") val results: List<MovieListResponse>
)