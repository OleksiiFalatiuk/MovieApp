package com.example.firstkotlinproject.model

data class MovieDetails(
    val id: Long,
    val years: Int,
    val name: String,
    val genre: List<Genre>,
    val review: Int,
    val isLiked: Boolean,
    val rating: Int,
    val detailImageRes: Unit,
    val storyLine: String,
    val actors: List<Actor>
)