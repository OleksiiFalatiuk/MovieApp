package com.example.firstkotlinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstkotlinproject.data.MovieRepository
import com.example.firstkotlinproject.data.MovieRepositoryImpl
import com.example.firstkotlinproject.data.remote.retrofit.RetrofitDataSource
import com.example.firstkotlinproject.movie.FragmentMovieList
import com.example.firstkotlinproject.moviedetails.FragmentMovieDetails
import com.example.firstkotlinproject.provider.MovieProvider
import com.example.firstkotlinproject.provider.NetworkModule
import kotlinx.serialization.ExperimentalSerializationApi


class MainActivity : AppCompatActivity(),
    FragmentMovieList.MoviesListItemClickListener,
    FragmentMovieDetails.MovieDetailsBackClickListener,MovieProvider {

//    private val jsonMovieRepository = JsonMovieRepository(this)
    private val networkModule = NetworkModule()
    @ExperimentalSerializationApi
    private val retrofitDataSource = RetrofitDataSource(networkModule.api)
    @ExperimentalSerializationApi
    private val movieRepository = MovieRepositoryImpl(retrofitDataSource)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            toMoviesList()
        }
    }

    override fun onMovieSelected(movieId: Int,actorId: Int) {
        toMovieDetails(movieId,actorId)
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

    private fun toMovieDetails(movieId: Int,actorId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.flMain,
                FragmentMovieDetails.create(movieId,actorId),
                FragmentMovieDetails::class.java.simpleName
            )
            .addToBackStack("trans:${FragmentMovieDetails::class.java.simpleName}")
            .commit()
    }

    private fun backStack() {
        supportFragmentManager.popBackStack()
    }

    @ExperimentalSerializationApi
    override fun provideMovie(): MovieRepository = movieRepository

}
