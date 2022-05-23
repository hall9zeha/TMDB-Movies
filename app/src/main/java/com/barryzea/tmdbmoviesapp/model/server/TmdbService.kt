package com.barryzea.tmdbmoviesapp.model.server


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 18/05/2022.
 * Copyright (c)  All rights reserved.
 ***/
interface TmdbService {
    @GET("discover/movie")
     fun listPopularMoviesAsync(
        @Query("api_key") apyKey:String,
        @Query("page")page:Int,
        @Query("language")language:String

    ): Call<TmdbList>


}