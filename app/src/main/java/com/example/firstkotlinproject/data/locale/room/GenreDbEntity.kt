package com.example.firstkotlinproject.data.locale.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "Genre",
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
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "detailsId", index = true)
    val detailsId: Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(defaultValue = "0")
    var primKey: Int = 0
)