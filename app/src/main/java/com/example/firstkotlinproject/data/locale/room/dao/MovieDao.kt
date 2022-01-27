package com.example.firstkotlinproject.data.locale.room.dao

import androidx.room.*
import com.example.firstkotlinproject.data.locale.room.GenreDbEntity
import com.example.firstkotlinproject.data.locale.room.MovieDbEntity
import com.example.firstkotlinproject.data.locale.room.MovieWithGenres
import com.example.firstkotlinproject.model.Movie

@Dao
abstract class MovieDao {
    @Transaction
    @Query("SELECT * FROM Movie")
    abstract fun getMovies(): List<MovieWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(movies: List<MovieDbEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertGenres(genre: List<GenreDbEntity>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(genre: List<MovieWithGenres>)



}