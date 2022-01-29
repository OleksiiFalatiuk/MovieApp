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
    version = 7,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2), AutoMigration(from = 2, to = 3), AutoMigration(
            from = 3,
            to = 4
        ),
        AutoMigration(from = 4, to = 5), AutoMigration(from = 5, to = 6), AutoMigration(from = 6, to = 7)

//        , AutoMigration(from = 7,to = 8,spec = AppDataBase.migration7_8::class),
//        AutoMigration(from = 8, to = 9, spec = AppDataBase.migration8_9::class),
//        AutoMigration(from = 9, to = 10, spec = AppDataBase.migration9_10::class),
//    AutoMigration(from = 10,to = 11), AutoMigration(from = 11, to = 12)
    ]
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getMoviesDao(): MovieDao
    abstract fun getMovieDetailsDao(): MovieDetailsDao

    @InternalCoroutinesApi
    companion object {
        private var instance: AppDataBase? = null
        private const val DATABASE_NAME = "Films.db"


        fun getInstance(context: Context): AppDataBase {
            if (instance == null) {
                synchronized(AppDataBase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDataBase::class.java,
                            DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return instance!!
        }
    }


//    @RenameColumn.Entries(
//        RenameColumn(
//            tableName = "MovieDetails",
//            fromColumnName = "asd",
//            toColumnName = "aaa"
//        )
//
//    )
//    class migration7_8: AutoMigrationSpec
//
//
//    @DeleteColumn.Entries(
//        DeleteColumn(
//            tableName = "MovieDetails",
//            columnName = "aaa"
//        )
//    )
//    class migration8_9: AutoMigrationSpec
//
//    @DeleteColumn.Entries(
//        DeleteColumn(
//            tableName = "MovieDetails",
//            columnName = "aaa"
//        )
//    )
//    class migration9_10: AutoMigrationSpec

}