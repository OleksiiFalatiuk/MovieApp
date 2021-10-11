package com.example.firstkotlinproject.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinproject.R
import com.example.firstkotlinproject.data.ActorData

class MovieDetailAdapter: RecyclerView.Adapter<MovieDetailAdapter.ViewHolder>() {

    private var listDetail = listOf<ActorData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieDetailAdapter.ViewHolder, position: Int) {
        val detail = listDetail[position]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.imageDetail?.setImageDrawable(holder.itemView.context.getDrawable(detail.imageDetail))
            holder.detailName?.text = detail.detailName
            holder.detailAge?.text = detail.detailAge
            holder.tag?.text = detail.tag
            holder.detailStar1?.setImageDrawable(holder.itemView.context.getDrawable(detail.detailStar1))
            holder.detailStar2?.setImageDrawable(holder.itemView.context.getDrawable(detail.detailStar2))
            holder.detailStar3?.setImageDrawable(holder.itemView.context.getDrawable(detail.detailStar3))
            holder.detailStar4?.setImageDrawable(holder.itemView.context.getDrawable(detail.detailStar4))
            holder.detailStar5?.setImageDrawable(holder.itemView.context.getDrawable(detail.detailStar5))
            holder.detailReviews?.text = detail.detailReviews
            holder.detailStory?.text = detail.story
            holder.actor1?.setImageDrawable(holder.itemView.context.getDrawable(detail.actor1))
            holder.actor2?.setImageDrawable(holder.itemView.context.getDrawable(detail.actor2))
            holder.actor3?.setImageDrawable(holder.itemView.context.getDrawable(detail.actor3))
            holder.actor4?.setImageDrawable(holder.itemView.context.getDrawable(detail.actor4))
            holder.nameActor1?.text = detail.nameActor1
            holder.nameActor2?.text = detail.nameActor2
            holder.nameActor3?.text = detail.nameActor3
            holder.nameActor4?.text = detail.nameActor4
        }
    }

    fun bindActorsDetail(newDetail: List<ActorData>) {
        listDetail = newDetail
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listDetail.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageDetail: ImageView? = itemView.findViewById(R.id.imageDetail)
        val detailName: TextView? = itemView.findViewById(R.id.detailName)
        val detailAge: TextView? = itemView.findViewById(R.id.yearsDetail)
        val tag: TextView? = itemView.findViewById(R.id.tag)
        val detailStar1: ImageView? = itemView.findViewById(R.id.detailStar1)
        val detailStar2: ImageView? = itemView.findViewById(R.id.detailStar2)
        val detailStar3: ImageView? = itemView.findViewById(R.id.detailStar3)
        val detailStar4: ImageView? = itemView.findViewById(R.id.detailStar4)
        val detailStar5: ImageView? = itemView.findViewById(R.id.detailStar5)
        val detailReviews: TextView? = itemView.findViewById(R.id.detailReviews)
        val detailStory: TextView? = itemView.findViewById(R.id.detailStory)
        val actor1: ImageView? = itemView.findViewById(R.id.ivDetail1)
        val actor2: ImageView? = itemView.findViewById(R.id.ivDetail2)
        val actor3: ImageView? = itemView.findViewById(R.id.ivDetail3)
        val actor4: ImageView? = itemView.findViewById(R.id.ivDetail4)
        val nameActor1: TextView? = itemView.findViewById(R.id.tvDetail1)
        val nameActor2: TextView? = itemView.findViewById(R.id.tvDetail2)
        val nameActor3: TextView? = itemView.findViewById(R.id.tvDetail3)
        val nameActor4: TextView? = itemView.findViewById(R.id.tvDetail4)

    }
}