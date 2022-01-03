package com.example.firstkotlinproject.data.locale.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "MovieDetails",
    foreignKeys = [ForeignKey(
        entity = MovieDbEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("detailsId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class MovieDetailsDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "detailsId")
    val detailsId: Long,
    val years: Int,
    val name: String,
    val review: Int,
    val isLiked: Boolean,
    val rating: Int,
    val detailImageRes: String?,
    val storyLine: String
)