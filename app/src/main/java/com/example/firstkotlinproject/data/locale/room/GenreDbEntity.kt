package com.example.firstkotlinproject.data.locale.room

import androidx.room.*


@Entity(tableName = "Genre",
    foreignKeys = [ForeignKey(
        entity = MovieDbEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("detailsId"),
        onDelete = ForeignKey.NO_ACTION
    )]
)
data class GenreDbEntity(
//    @PrimaryKey
//    @ColumnInfo(name = "id", index = true)
//    val id: Int,
    val name: String,
    @PrimaryKey
    @ColumnInfo(name = "detailsId", index = true)
    val detailsId: Int
)