package com.example.firstkotlinproject.data.locale.room

import androidx.room.*
import com.example.firstkotlinproject.model.Genre


@Entity(tableName = "Movie")
data class MovieDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    val years: Int,
    val name: String,
    val time: Int,
    val review: Long,
    val isLiked: Boolean,
    val rating: Int,
    val avatar: String?,
//    @Embedded
//    val genre: List<GenreDbEntity>
)