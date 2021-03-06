package com.example.firstkotlinproject.data.locale.room

import androidx.room.Embedded
import androidx.room.Relation


data class MovieWithGenres (
    @Embedded
    val movie: MovieDbEntity,
    @Relation(parentColumn = "id", entityColumn = "detailsId")
    val genres: List<GenreDbEntity>
)