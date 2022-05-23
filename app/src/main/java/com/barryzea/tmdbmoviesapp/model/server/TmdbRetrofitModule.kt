package com.barryzea.tmdbmoviesapp.model.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 18/05/2022.
 * Copyright (c)  All rights reserved.
 ***/
/*@Module
@InstallIn(SingletonComponent::class)*/
class TmdbRetrofitModule:TmdbRetrofitInterface {
    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }
   /* @Provides
    @Singleton*/
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
   // @Provides
   // @Singleton
    fun provideMoviesApiClient():TmdbService{
        return  provideRetrofit().create(TmdbService::class.java)
    }

    override  fun retrofitService() :TmdbService{
       val service = Retrofit.Builder()
           .baseUrl("https://api.themoviedb.org/3/")
           .client(okHttpClient)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
       return service.create(TmdbService::class.java)
       /*.run {
           create<TmdbService>(TmdbService::class.java)
       }*/
   }
}