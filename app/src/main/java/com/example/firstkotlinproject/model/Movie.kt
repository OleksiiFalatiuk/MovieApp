package com.example.firstkotlinproject.model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Movie(
    val id: Int,
    val years: Int,
    val name: String,
    val genre: List<Genre>,
    val time: Int,
    val review: Int,
    val isLiked: Boolean,
    val rating: Int,
    val avatar: String?
)

