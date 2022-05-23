package com.barryzea.tmdbmoviesapp.di

import android.app.Application
import com.barryzea.tmdbmoviesapp.R
import com.barryzea.tmdbmoviesapp.model.repository.TmdbDataSource
import com.barryzea.tmdbmoviesapp.model.repository.TmdbDataSourceImpl
import com.barryzea.tmdbmoviesapp.model.server.TmdbRetrofitInterface
import com.barryzea.tmdbmoviesapp.model.server.TmdbRetrofitModule
import com.barryzea.tmdbmoviesapp.model.server.TmdbService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 18/05/2022.
 * Copyright (c)  All rights reserved.
 ***/
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    @Named("apiKey")
    //Agregar tu clave de la API TMDB
    fun apiKeyProvider(app:Application):String= app.getString(R.string.apiKey)

    @Provides
    @Singleton
    @Named("language")
    fun languageProvider():String= Locale.getDefault().language.toString()

    @Provides
    fun remoteDatasourceProvides():TmdbDataSource=TmdbDataSourceImpl()

    @Provides
    fun retrofitDataSourceProvides():TmdbRetrofitInterface = TmdbRetrofitModule()
}