package com.barryzea.tmdbmoviesapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.barryzea.tmdbmoviesapp.R
import com.barryzea.tmdbmoviesapp.databinding.ActivityDetailBinding
import com.barryzea.tmdbmoviesapp.model.server.Movie
import com.barryzea.tmdbmoviesapp.ui.common.loadUrl

class DetailActivity : AppCompatActivity() {
    companion object{
        const val MOVIE="DetailActivity"
    }
    private  var movie:Movie ?=null
    private lateinit var bind:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)
        movie= intent?.getParcelableExtra<Movie>(MOVIE)!!
        updateUIDetail()
    }
    private fun updateUIDetail()=with(bind){
        movie?.let{
            ivMovieDetail.loadUrl("https://image.tmdb.org/t/p/w780${it.backdropPath}")
            tvMovieDetailDesc.text=it.overview
            toolbar.title=it.title
            tvDetail.setMovieInfo(it)
        }
    }
}