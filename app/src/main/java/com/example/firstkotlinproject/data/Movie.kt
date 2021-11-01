package com.example.firstkotlinproject.data

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Movie(
    val id: Int,
    val years: Int,
    val name: String,
    val genre: String,
    val time: String,
    val review: Int,
    @DrawableRes val star1: Int,
    @DrawableRes val star2: Int,
    @DrawableRes val star3: Int,
    @DrawableRes val star4: Int,
    @DrawableRes val star5: Int,
    @DrawableRes val avatar: Int,
    @DrawableRes val detailImageRes: Int,
    val storyLine: String,
    val actors: List<ActorData>
) : Serializable

