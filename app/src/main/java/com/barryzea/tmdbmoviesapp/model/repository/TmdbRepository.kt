package com.barryzea.tmdbmoviesapp.model.repository

import androidx.lifecycle.MutableLiveData
import com.barryzea.tmdbmoviesapp.model.server.Movie
import com.barryzea.tmdbmoviesapp.model.server.TmdbList
import com.barryzea.tmdbmoviesapp.model.server.TmdbRetrofitInterface
import com.barryzea.tmdbmoviesapp.ui.common.SingleMutableLiveData

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 18/05/2022.
 * Copyright (c)  All rights reserved.
 ***/

class TmdbRepository(private val tmdbDataSource: TmdbDataSource,
                     private val apiKey:String,
                     private val tmdbRetrofit: TmdbRetrofitInterface,
                     private val language:String) {
        suspend fun callMovies(currentPage:Int){
            tmdbDataSource.callTmdbMovies(apiKey, tmdbRetrofit, currentPage,language)
        }
        fun getPopularTmdbMovies(): SingleMutableLiveData<TmdbList> {

            return tmdbDataSource.getListMovies()
        }
}