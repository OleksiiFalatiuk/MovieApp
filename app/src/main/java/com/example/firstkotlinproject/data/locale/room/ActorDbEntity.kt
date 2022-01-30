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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(defaultValue = "0")
    val primeActorKey: Int = 0,
    val name: String,
    val imageRes: String?,
    @ColumnInfo(name = "detailsId", index = true)
    val detailsId: Int
)