package com.example.firstkotlinproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinproject.adapters.MovieDetailAdapter


class FragmentMoviesDetails : Fragment() {

    private var recyclerDetail: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerDetail =  view.findViewById(R.id.rvDetail)
        recyclerDetail?.adapter = MovieDetailAdapter()
    }

    override fun onStart() {
        updateData()
        super.onStart()
    }

    private fun updateData() {
        (recyclerDetail?.adapter as? MovieDetailAdapter)?.apply {
            bindActorsDetail(DetailData().getDetail())
        }
    }


}