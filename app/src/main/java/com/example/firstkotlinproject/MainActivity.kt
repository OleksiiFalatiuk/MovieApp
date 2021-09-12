package com.example.firstkotlinproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView


class MainActivity : AppCompatActivity(), FragmentMovieList.SomeFragmentClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


     supportFragmentManager.beginTransaction().apply {
         add(R.id.flMain,FragmentMovieList())
         commit()
     }

    }

    override fun changeFragment() {
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(null)
            add(R.id.flMain,FragmentMoviesDetails())
                .commit()
        }
    }
}