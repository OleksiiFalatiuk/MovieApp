package com.example.firstkotlinproject.data.locale.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "Genre",
    foreignKeys = [ForeignKey(
        entity = MovieDbEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("genreId"),
        onDelete = ForeignKey.NO_ACTION
    )]
)
data class GenreDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    val name: String,
    val genreId: Long
)