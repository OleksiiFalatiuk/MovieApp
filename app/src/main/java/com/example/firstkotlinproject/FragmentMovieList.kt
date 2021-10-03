package com.example.firstkotlinproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinproject.adapters.MovieListAdapter
import com.example.firstkotlinproject.domain.MovieData


class FragmentMovieList : Fragment() {

    private var recycler: RecyclerView? = null
//    private var someFragmentClickListener: SomeFragmentClickListener? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

//        view?.findViewById<FrameLayout>(R.id.movie)?.apply {
//            setOnClickListener{
//                someFragmentClickListener?.changeFragment()
//            }
//        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler =  view.findViewById(R.id.rvActors)
        recycler?.adapter = MovieListAdapter()
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


//    override fun onDetach() {
//        super.onDetach()
//        someFragmentClickListener = null
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is SomeFragmentClickListener)
//            someFragmentClickListener = context
//    }


//    interface SomeFragmentClickListener{
//        fun changeFragment()
//    }

}