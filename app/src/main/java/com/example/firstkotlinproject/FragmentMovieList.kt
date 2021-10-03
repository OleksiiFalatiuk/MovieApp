package com.example.firstkotlinproject

import android.content.Context
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinproject.adapters.MovieListAdapter
import com.example.firstkotlinproject.domain.MovieData


class FragmentMovieList : Fragment(), MovieListAdapter.ItemClickListener {
    private var recycler: RecyclerView? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.rvActors)
        recycler?.adapter = MovieListAdapter()
        (recycler?.adapter as? MovieListAdapter)?.itemclick = this

    }

    override fun onStart() {
        updateData()
        super.onStart()
    }

    private fun updateData() {
        (recycler?.adapter as? MovieListAdapter)?.apply {
            bindActors(MovieData().getMovie())
        }
    }

    override fun onItemClick() {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.flMain, FragmentMoviesDetails(), null)
            addToBackStack(null)
                .commit()
        }



    }
}