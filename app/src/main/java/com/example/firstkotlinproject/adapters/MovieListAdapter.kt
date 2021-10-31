package com.example.firstkotlinproject.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinproject.R
import com.example.firstkotlinproject.data.Movie
import com.example.firstkotlinproject.domain.MovieData

class MovieListAdapter(private val onClickCard: (item: Movie) -> Unit) :
    ListAdapter<Movie,MovieListAdapter.ViewHolder>(DiffCallback()) {

    private var list = listOf<Movie>()
//    var itemclick: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie,onClickCard)
//        holder.itemView.apply {
//            setOnClickListener {
//                itemclick?.onItemClick(movie)
//            }
//        }
    }


//    fun bindActors(newMovie: List<Movie>) {
//        list = newMovie
//        notifyDataSetChanged()
//    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: ImageView? = itemView.findViewById(R.id.ivMain)
        private val years: TextView? = itemView.findViewById(R.id.tvYears)
        private val genre: TextView? = itemView.findViewById(R.id.tvGenre)
        private val star1: ImageView? = itemView.findViewById(R.id.ivStar1)
        private val star2: ImageView? = itemView.findViewById(R.id.ivStar2)
        private val star3: ImageView? = itemView.findViewById(R.id.ivStar3)
        private val star4: ImageView? = itemView.findViewById(R.id.ivStar4)
        private val star5: ImageView? = itemView.findViewById(R.id.ivStar5)
        private val review: TextView? = itemView.findViewById(R.id.tvReviews)
        private val name: TextView? = itemView.findViewById(R.id.tvName)
        private val time: TextView? = itemView.findViewById(R.id.tvTime)

        fun bind(item: Movie,onClickCard: (item: Movie) -> Unit) {
            avatar?.setImageResource(item.avatar)
            years?.text =
                itemView.context.getString(R.string._13, item.years)
            genre?.text = item.genre
            star1?.setImageResource(item.star1)
            star2?.setImageResource(item.star2)
            star3?.setImageResource(item.star3)
            star4?.setImageResource(item.star4)
            star5?.setImageResource(item.star5)
            review?.text =
                itemView.context.getString(R.string._125_reviews, item.review)
            name?.text = item.name
            time?.text = item.time

            itemView.setOnClickListener {
                onClickCard(item)
            }
        }

    }

//    interface ItemClickListener {
//        fun onItemClick(movieData: Movie)
//    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }


    }
}

