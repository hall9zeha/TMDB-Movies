package com.barryzea.tmdbmoviesapp.ui.common

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 22/05/2022.
 * Copyright (c)  All rights reserved.
 ***/

abstract class CallBackWithRetry<T>(call: Call<T>):Callback<T> {
    private val TOTAL_RETRIES_ENTRY=10
    private val TAG= CallBackWithRetry::class.java.simpleName
    var callMain:Call<T>?= call
    private var retryCount=0

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (!response.isSuccessful && retryCount++ < TOTAL_RETRIES_ENTRY) {
            retry()
        } else {
            onFinalResponse(call, response, retryCount)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        if(retryCount++<TOTAL_RETRIES_ENTRY){
            retry()
        }
        else{
            onFinalFailure(call,t, retryCount)
        }
    }

    private fun retry(){
        callMain?.clone()?.enqueue(this)
    }
    open fun onFinalResponse(call: Call<T>,response: Response<T>, numRetry: Int){}
    open fun onFinalFailure(call:Call<T>, t:Throwable, numRetry:Int){}

}