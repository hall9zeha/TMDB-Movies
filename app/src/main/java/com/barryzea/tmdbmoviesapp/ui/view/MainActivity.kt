package com.barryzea.tmdbmoviesapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.barryzea.tmdbmoviesapp.R
import com.barryzea.tmdbmoviesapp.databinding.ActivityDetailBinding
import com.barryzea.tmdbmoviesapp.databinding.ActivityMainBinding
import com.barryzea.tmdbmoviesapp.model.server.Movie
import com.barryzea.tmdbmoviesapp.model.server.TmdbList
import com.barryzea.tmdbmoviesapp.ui.common.PaginationRecyclerView
import com.barryzea.tmdbmoviesapp.ui.common.startActivity
import com.barryzea.tmdbmoviesapp.viewModel.AdapterMovies
import com.barryzea.tmdbmoviesapp.viewModel.OnClickMovie
import com.barryzea.tmdbmoviesapp.viewModel.TmdbViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClickMovie{
    private lateinit var bind:ActivityMainBinding
    private lateinit var adapter:AdapterMovies
    private val viewModel:TmdbViewModel by viewModels()
    private lateinit var layoutManager:GridLayoutManager
    private var currentPage=1
    private var isLoading=false
    private var totalPages=0
    private var isLastPage=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        setUpAdapter()
        setUpPaginationScroll()

        viewModel.callRemoteDataSource(1)
        viewModel.getMoviesTmdb().observe(this, Observer(::updateUI))
        bind.fabMain.setOnClickListener { bind.rvMovies.scrollToPosition(0) }
    }
    private fun setUpAdapter() {
        adapter=AdapterMovies(this)
        layoutManager= GridLayoutManager(this,3)
        bind.rvMovies.apply {
            adapter=this@MainActivity.adapter
            layoutManager=this@MainActivity.layoutManager
            setHasFixedSize(true)
        }
    }

    private fun updateUI(result: TmdbList?) {
        bind.pbLoading.visibility= if(result==null) View.VISIBLE else View.GONE
        result?.let{

            if(isLoading && it.movies.size>0){adapter.removeLoadingItem()}
            totalPages=it.total_pages
            if(currentPage >=totalPages) isLastPage=true
            isLoading=false

            adapter.addAll(it.movies)
        }

    }

    private fun setUpPaginationScroll() {
        bind.rvMovies.setOnScrollListener(object: PaginationRecyclerView(layoutManager, bind.fabMain){
            override fun loadMoreItems() {
                isLoading=true
                currentPage+=1
                adapter.addLoadingItem()
                viewModel.callRemoteDataSource(currentPage)

            }

            override fun getTotalPageCount(): Int {
                return totalPages
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })
    }

    override fun onClick(movie: Movie) {
        startActivity<DetailActivity>{
            putExtra(DetailActivity.MOVIE, movie)
        }
    }
}