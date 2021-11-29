package com.example.firstkotlinproject.model

data class Movie(
    val id: Long,
    val years: Int,
    val name: String,
    val genre: List<Genre>,
    val time: Int,
    val review: Long,
    val isLiked: Boolean,
    val rating: Int,
    val avatar: Unit
)

