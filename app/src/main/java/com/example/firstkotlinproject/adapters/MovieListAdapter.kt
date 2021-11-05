package com.example.firstkotlinproject.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstkotlinproject.R
import com.example.firstkotlinproject.model.Movie

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
        private val likeImage: ImageView = itemView.findViewById(R.id.ivHeart)
        private val starsImages: List<ImageView> = listOf(
            itemView.findViewById(R.id.ivStar1),
            itemView.findViewById(R.id.ivStar2),
            itemView.findViewById(R.id.ivStar3),
            itemView.findViewById(R.id.ivStar4),
            itemView.findViewById(R.id.ivStar5)
        )
        private val review: TextView? = itemView.findViewById(R.id.tvReviews)
        private val name: TextView? = itemView.findViewById(R.id.tvName)
        private val time: TextView? = itemView.findViewById(R.id.tvTime)

        fun bind(item: Movie,onClickCard: (item: Movie) -> Unit) {
//            avatar?.setImageResource(item.avatar)
            if (avatar != null) {
                Glide.with(itemView).load(item.avatar).into(avatar)
            }
            val context = itemView.context
            years?.text =
                itemView.context.getString(R.string._13, item.years)
            genre?.text = item.genre.joinToString { it.name }
            review?.text =
                itemView.context.getString(R.string._125_reviews, item.review)
            name?.text = item.name
            time?.text = context.getString(R.string.movies_list_film_time, item.time)

            val likeColor = if (item.isLiked) {
                R.color.pink
            } else {
                R.color.white
            }
            ImageViewCompat.setImageTintList(
                likeImage, ColorStateList.valueOf(
                    ContextCompat.getColor(likeImage.context, likeColor)
                )
            )

            //set stars tint
            starsImages.forEachIndexed { index, imageView ->
                val colorId = if (item.rating > index) R.color.pink else R.color.gray_dark
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(imageView.context, colorId)
                    )
                )
            }

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

