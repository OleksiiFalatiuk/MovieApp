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
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id",)
//    val id: Int,
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "detailsId", index = true)
    val detailsId: Int
)