package com.example.firstkotlinproject.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MovieDetailsResponse(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("genres")
    val genres: List<GenreResponse>,
    @SerialName("id")
    val id: Long,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("runtime")
    val runtime: Long
)