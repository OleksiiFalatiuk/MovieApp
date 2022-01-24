package com.example.firstkotlinproject.data.locale.room

import androidx.room.Embedded
import androidx.room.Relation


data class MovieDetailsWithGenresAndActors(
    @Embedded
    val movieDetails: MovieDetailsDbEntity,
    @Relation(parentColumn = "detailsId", entityColumn = "detailsId")
    val genre: List<GenreDbEntity>,
    @Relation(parentColumn = "detailsId", entityColumn = "detailsId")
    val actors: List<ActorDbEntity>
)