package com.example.firstkotlinproject.data.locale.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.firstkotlinproject.data.locale.room.ActorDbEntity
import com.example.firstkotlinproject.data.locale.room.MovieDetailsDbEntity
import com.example.firstkotlinproject.data.locale.room.MovieDetailsWithGenresAndActors

@Dao
interface MovieDetailsDao {
    @Transaction
    @Query("SELECT * FROM MovieDetails")
    fun getMovieDetails(): List<MovieDetailsWithGenresAndActors>

    @Insert()
    fun insertMovieDetails(details: List<MovieDetailsDbEntity>)
}