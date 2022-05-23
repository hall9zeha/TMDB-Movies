package com.barryzea.tmdbmoviesapp.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barryzea.tmdbmoviesapp.model.server.Movie
import com.barryzea.tmdbmoviesapp.model.server.TmdbList
import com.barryzea.tmdbmoviesapp.ui.common.ScopedViewModel
import com.barryzea.tmdbmoviesapp.ui.common.SingleMutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 18/05/2022.
 * Copyright (c)  All rights reserved.
 ***/
@HiltViewModel
class TmdbViewModel @Inject constructor(private val getPopularMovies: GetPopularMoviesTmdb ): ScopedViewModel( ) {


   init{
        initScope()
    }
    fun callRemoteDataSource(currentPage:Int){
        launch {
            getPopularMovies.callMovies(currentPage)
        }
    }

    fun getMoviesTmdb():SingleMutableLiveData<TmdbList>{
           return getPopularMovies.invoke()
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}