package com.barryzea.tmdbmoviesapp.viewModel

import androidx.lifecycle.MutableLiveData
import com.barryzea.tmdbmoviesapp.model.repository.TmdbRepository
import com.barryzea.tmdbmoviesapp.model.server.Movie
import com.barryzea.tmdbmoviesapp.model.server.TmdbList
import com.barryzea.tmdbmoviesapp.ui.common.SingleMutableLiveData

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 18/05/2022.
 * Copyright (c)  All rights reserved.
 ***/

class GetPopularMoviesTmdb(private val repository:TmdbRepository) {
    suspend fun callMovies(currentPage:Int){repository.callMovies(currentPage)}
    fun invoke(): SingleMutableLiveData<TmdbList> = repository.getPopularTmdbMovies()
}