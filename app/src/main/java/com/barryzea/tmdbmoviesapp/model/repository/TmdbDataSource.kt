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

interface TmdbDataSource {
    suspend fun callTmdbMovies(apikey:String, tmdbRetrofit:TmdbRetrofitInterface, currentPage:Int, language:String)
      fun getListMovies(): SingleMutableLiveData<TmdbList>
}