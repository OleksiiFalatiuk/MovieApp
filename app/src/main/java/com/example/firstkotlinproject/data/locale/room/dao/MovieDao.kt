package com.example.firstkotlinproject.data.locale.room.dao

import androidx.room.*
import com.example.firstkotlinproject.data.locale.room.GenreDbEntity
import com.example.firstkotlinproject.data.locale.room.MovieDbEntity
import com.example.firstkotlinproject.data.locale.room.MovieWithGenres

@Dao
interface MovieDao {
    @Transaction
    @Query("SELECT * FROM Movie")
    fun getMovies(): List<MovieWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: MovieDbEntity)

    @Insert
    fun insertGenres(genre: List<GenreDbEntity>)

    @Update
    fun updateGenres(genre: List<GenreDbEntity>)

    @Query("delete from Genre where detailsId like :id")
    fun deleteOldGenreFromFilms(id: Int)


}