package com.example.firstkotlinproject.data.locale.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.firstkotlinproject.data.locale.room.MovieDbEntity
import com.example.firstkotlinproject.data.locale.room.MovieWithGenres

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getMovies(): List<MovieWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieDbEntity>)
}