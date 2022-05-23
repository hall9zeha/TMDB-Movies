package com.barryzea.tmdbmoviesapp.viewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.barryzea.tmdbmoviesapp.R
import com.barryzea.tmdbmoviesapp.databinding.ItemMovieBinding
import com.barryzea.tmdbmoviesapp.model.server.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 19/05/2022.
 * Copyright (c)  All rights reserved.
 ***/

class AdapterMovies(private val listener :OnClickMovie): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private var movies:ArrayList<Movie> = arrayListOf()
    private lateinit var context:Context
    private var isLoading:Boolean=false
    private val VIEW_TYPE_NORMAL=1
    private val VIEW_TYPE_LOADING=2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context=parent.context
        val itemView=LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false)
        val itemLoading=LayoutInflater.from(context).inflate(R.layout.item_loading,parent, false)

        return when(viewType){
            VIEW_TYPE_NORMAL->ViewHolder(itemView)
        else ->ViewHolderLoading(itemLoading)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            VIEW_TYPE_NORMAL -> {
                (holder as ViewHolder).bind(movies[position])
                holder.itemView.setOnClickListener { listener.onClick(movies[position])}
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position==movies.size-1 && isLoading){
            VIEW_TYPE_LOADING
        } else{
            VIEW_TYPE_NORMAL
        }

    }
    override fun getItemCount(): Int = movies?.size ?: 0

    fun add(m:Movie){
        if(!movies.contains(m)){
            movies.add(m)
            notifyItemInserted(movies.indexOf(m))
        }
    }
    fun addAll(movies: ArrayList<Movie>){
        for(m in movies){
            add(m)
        }
    }
    fun addLoadingItem(){
        isLoading=true
        add(Movie())
    }
    fun removeLoadingItem(){
        isLoading=false
        val position=movies.size-1
        val movie:Movie=getItem(position)
        movie?.let{
            movies.removeAt(position)
            notifyItemRemoved(position)
        }
    }
    fun getItem(position:Int):Movie{
        return movies[position]
    }
    class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        private val binding=ItemMovieBinding.bind(itemView)
        fun bind(movie:Movie)=with(binding){
            val animation=AnimationUtils.loadAnimation(root.context,R.anim.amination_item_movie)
            root.startAnimation(animation)

            Glide.with(root).load("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
                .placeholder(R.drawable.placeholder_movie)
                .error(R.drawable.placeholder_movie)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivMovie)
            tvTitle.text = movie.title

        }
    }
    class ViewHolderLoading(itemView:View) :RecyclerView.ViewHolder(itemView){

    }

}