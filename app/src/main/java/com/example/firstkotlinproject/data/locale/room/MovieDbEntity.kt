package com.example.firstkotlinproject.data.locale.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Movie")
data class MovieDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    val years: Int,
    val name: String,
    val time: Int,
    val review: Long,
    val isLiked: Boolean,
    val rating: Int,
    val avatar: String?
)