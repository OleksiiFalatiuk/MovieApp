package com.example.firstkotlinproject.data.locale.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "Actor",
    foreignKeys = [ForeignKey(
        entity = MovieDbEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("detailsId"),
        onDelete = ForeignKey.CASCADE
    )])
data class ActorDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "id", index = true)
    val id: Int,
    val name: String,
    val imageRes: String?,
    @ColumnInfo(name = "detailsId", index = true)
    val detailsId: Int
)