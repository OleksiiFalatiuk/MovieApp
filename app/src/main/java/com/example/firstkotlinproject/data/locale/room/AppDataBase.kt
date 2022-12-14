package com.example.firstkotlinproject.data.locale.room

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import com.example.firstkotlinproject.data.locale.room.dao.MovieDao
import com.example.firstkotlinproject.data.locale.room.dao.MovieDetailsDao
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(
    entities = [MovieDbEntity::class, MovieDetailsDbEntity::class, GenreDbEntity::class, ActorDbEntity::class],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getMoviesDao(): MovieDao
    abstract fun getMovieDetailsDao(): MovieDetailsDao

    companion object {
        fun getInstance(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, "Films.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}