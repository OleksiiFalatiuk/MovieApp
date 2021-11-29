package com.example.firstkotlinproject.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MovieListResponse(
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("genre_ids")
    val genreId: List<Long>,
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("vote_count")
    val voteCount: Long,
    @SerialName("popularity")
    val popularity: Double,
)