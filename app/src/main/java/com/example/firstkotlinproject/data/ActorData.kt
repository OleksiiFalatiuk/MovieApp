package com.example.firstkotlinproject.data

import androidx.annotation.DrawableRes

data class ActorData(
    val id: Int,
    val name: String,
    @DrawableRes
    val imageRes: Int
)