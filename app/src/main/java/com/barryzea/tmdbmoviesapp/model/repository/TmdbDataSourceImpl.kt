package com.barryzea.tmdbmoviesapp.model.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.barryzea.tmdbmoviesapp.model.server.Movie
import com.barryzea.tmdbmoviesapp.model.server.TmdbList
import com.barryzea.tmdbmoviesapp.model.server.TmdbRetrofitInterface
import com.barryzea.tmdbmoviesapp.model.server.TmdbRetrofitModule
import com.barryzea.tmdbmoviesapp.ui.common.CallBackWithRetry
import com.barryzea.tmdbmoviesapp.ui.common.SingleMutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 18/05/2022.
 * Copyright (c)  All rights reserved.
 ***/

class TmdbDataSourceImpl: TmdbDataSource {

    private var listMovies: SingleMutableLiveData<TmdbList> = SingleMutableLiveData()
    override suspend fun callTmdbMovies(
        apikey: String,
        retrofitModule: TmdbRetrofitInterface,
        currentPage: Int,
        language:String
    ) =
        withContext(Dispatchers.IO) {
            val apiService = retrofitModule.retrofitService().listPopularMoviesAsync(apikey, currentPage, language)
            apiService.enqueue(object : CallBackWithRetry<TmdbList>(apiService) {
                override fun onFinalResponse(
                    call: Call<TmdbList>,
                    response: Response<TmdbList>,
                    numRetry: Int
                ) {
                    super.onFinalResponse(call, response, numRetry)
                    listMovies.value = response.body()
                }

                override fun onFinalFailure(call: Call<TmdbList>, t: Throwable, numRetry: Int) {
                    super.onFinalFailure(call, t, numRetry)
                    Log.e("PRUEBA", t.message.toString())
                }
                /*override fun onResponse(call: Call<TmdbList>, response: Response<TmdbList>) {
                    listMovies.value=response.body()

                }

                override fun onFailure(call: Call<TmdbList>, t: Throwable) {
                    Log.e("PRUEBA", t.message.toString() )
                }*/

            })
        }

    override fun getListMovies(): SingleMutableLiveData<TmdbList> {
        return listMovies
    }
}