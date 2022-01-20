package com.example.firstkotlinproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.firstkotlinproject.data.MovieRepository
import com.example.firstkotlinproject.data.locale.room.RoomData
import com.example.firstkotlinproject.data.remote.retrofit.RetrofitDataSource
import com.example.firstkotlinproject.movie.FragmentMovieList
import com.example.firstkotlinproject.moviedetails.FragmentMovieDetails
import com.example.firstkotlinproject.provider.MovieProvider
import com.example.firstkotlinproject.provider.NetworkModule
import com.example.firstkotlinproject.repository.MovieRepositoryImplNew
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.PrimitiveKind


class MainActivity : AppCompatActivity(),
    FragmentMovieList.MoviesListItemClickListener,
    FragmentMovieDetails.MovieDetailsBackClickListener,MovieProvider {


    companion object {
        private const val FRAGMENT_FILM = "film"
    }

    private val networkModule = NetworkModule()
    @ExperimentalSerializationApi
    private val remoteDataSource = RetrofitDataSource(networkModule.api)
    @InternalCoroutinesApi
    private val localeDataSource = RoomData(MovieGeneratorApp.appData)
    @ExperimentalSerializationApi
    @InternalCoroutinesApi
    private val movieRepository = MovieRepositoryImplNew(localeDataSource,remoteDataSource)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            toMoviesList()
            intent?.let(::handleIntent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null){
            handleIntent(intent)
        }
    }

    private fun handleIntent(intent: Intent){
        when(intent.action){
            Intent.ACTION_VIEW -> {
                val id = intent.data?.lastPathSegment?.toIntOrNull()
                if (id != null) {
                    toMovieDetails(id)
                }
            }
        }
    }

    override fun onMovieSelected(movieId: Int) {
        toMovieDetails(movieId)
    }

    override fun onMovieDeselected() {
        backStack()
    }

    private fun toMoviesList() {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.flMain,
                FragmentMovieList.create(),
                FragmentMovieList::class.java.simpleName
            )
            .addToBackStack("trans:${FragmentMovieList::class.java.simpleName}")
            .commit()
    }

    private fun toMovieDetails(movieId: Int) {
        supportFragmentManager.popBackStack(FRAGMENT_FILM, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.flMain,
                FragmentMovieDetails.create(movieId),
                FragmentMovieDetails::class.java.simpleName
            )
            .addToBackStack(FRAGMENT_FILM)
            .commit()
    }

    private fun backStack() {
        supportFragmentManager.popBackStack()
    }

    @InternalCoroutinesApi
    @ExperimentalSerializationApi
    override fun provideMovie(): MovieRepository = movieRepository

}
