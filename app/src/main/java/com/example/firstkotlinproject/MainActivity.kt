package com.example.firstkotlinproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvMain: TextView = findViewById(R.id.tvMain)
        tvMain.setOnClickListener{moveToNextActivity()}
    }
    private fun moveToNextActivity(){
        val intent = Intent(this,MovieDetailsActivity::class.java)
        startActivity(intent)
    }
}