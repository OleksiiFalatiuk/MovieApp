package com.example.firstkotlinproject.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinproject.R
import com.example.firstkotlinproject.data.Movie

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var list = listOf<Movie>()
    private var itemclick: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = list[position]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.avatar?.setImageDrawable(holder.itemView.context.getDrawable(movie.avatar))
            holder.years?.text = movie.years
            holder.heart?.setImageDrawable(holder.itemView.context.getDrawable(movie.heart_avatar))
            holder.genre?.text = movie.genre
            holder.star1?.setImageDrawable(holder.itemView.context.getDrawable(movie.star1))
            holder.star2?.setImageDrawable(holder.itemView.context.getDrawable(movie.star2))
            holder.star3?.setImageDrawable(holder.itemView.context.getDrawable(movie.star3))
            holder.star4?.setImageDrawable(holder.itemView.context.getDrawable(movie.star4))
            holder.star5?.setImageDrawable(holder.itemView.context.getDrawable(movie.star5))
            holder.review?.text = movie.reviews
            holder.name?.text = movie.name
            holder.time?.text = movie.time
            holder.avatar?.apply { setOnClickListener{
                itemclick?.onItemClick()
            } }
        }
    }

    fun bindActors(newMovie: List<Movie>) {
        list = newMovie
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val avatar: ImageView? = itemView.findViewById(R.id.ivMain)
        val years: TextView? = itemView.findViewById(R.id.tvYears)
        val heart: ImageView? = itemView.findViewById(R.id.ivHeart)
        val genre: TextView? = itemView.findViewById(R.id.tvGenre)
        val star1: ImageView? = itemView.findViewById(R.id.ivStar1)
        val star2: ImageView? = itemView.findViewById(R.id.ivStar2)
        val star3: ImageView? = itemView.findViewById(R.id.ivStar3)
        val star4: ImageView? = itemView.findViewById(R.id.ivStar4)
        val star5: ImageView? = itemView.findViewById(R.id.ivStar5)
        val review: TextView? = itemView.findViewById(R.id.tvReviews)
        val name: TextView? = itemView.findViewById(R.id.tvName)
        val time: TextView? = itemView.findViewById(R.id.tvTime)
        val rvActor: RecyclerView? = itemView.findViewById(R.id.rvActors)

    }

    interface ItemClickListener{
        fun onItemClick()
    }
}