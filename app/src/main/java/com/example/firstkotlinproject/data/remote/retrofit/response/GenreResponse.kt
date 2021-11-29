package com.example.firstkotlinproject.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    @SerialName("id")
    val idGenre: Long,
    @SerialName("name")
    val nameGenre: String
)