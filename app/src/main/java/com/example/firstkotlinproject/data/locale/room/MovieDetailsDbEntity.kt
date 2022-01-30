package com.example.firstkotlinproject.data.locale.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.firstkotlinproject.model.Actor


@Entity(tableName = "MovieDetails",
    foreignKeys = [ForeignKey(
        entity = MovieDbEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("detailsId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class MovieDetailsDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(defaultValue = "0")
    val detailsPrime: Int = 0,
    @ColumnInfo(name = "detailsId")
    val detailsId: Int,
    val years: Int,
    val name: String,
    val review: Int,
    val isLiked: Boolean,
    val rating: Int,
    val detailImageRes: String?,
    val storyLine: String
)