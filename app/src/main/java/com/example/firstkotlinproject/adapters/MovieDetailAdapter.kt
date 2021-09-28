package com.example.firstkotlinproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinproject.R
import com.example.firstkotlinproject.data.Movie
import com.example.firstkotlinproject.data.MovieDetail

class MovieDetailAdapter: RecyclerView.Adapter<MovieDetailAdapter.ViewHolder>() {

    private var listDetail = listOf<MovieDetail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieDetailAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = listDetail.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){


    }
}