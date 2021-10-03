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


class MainActivity : AppCompatActivity(), MovieListAdapter.ItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction().apply {
            add(R.id.flMain, FragmentMovieList())
            commit()
        }

    }

    override fun onItemClick() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flMain, FragmentMoviesDetails(),null)
            addToBackStack(null)
                .commit()
        }

//    override fun changeFragment() {
//        supportFragmentManager.beginTransaction().apply {
//            addToBackStack(null)
//            add(R.id.flMain,FragmentMoviesDetails())
//                .commit()
//        }
//    }
    }
}