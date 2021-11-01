package com.example.firstkotlinproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import com.example.firstkotlinproject.adapters.MovieListAdapter
import com.example.firstkotlinproject.data.Movie


class MainActivity : AppCompatActivity(),
    FragmentMovieList.MoviesListItemClickListener,
    FragmentMoviesDetails.MovieDetailsBackClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            toMoviesList()
        }

//        supportFragmentManager.beginTransaction().apply {
//            add(R.id.flMain, FragmentMovieList())
//            commit()
//        }

    }

    override fun onMovieSelected(movieData: Movie) {
        toMovieDetails(movieData)
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

    private fun toMovieDetails(movieData: Movie) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.flMain,
                FragmentMoviesDetails.create(movieData),
                FragmentMoviesDetails::class.java.simpleName
            )
            .addToBackStack("trans:${FragmentMoviesDetails::class.java.simpleName}")
            .commit()
    }

    private fun backStack() {
        supportFragmentManager.popBackStack()
    }

}
