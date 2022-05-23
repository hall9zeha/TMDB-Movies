package com.barryzea.tmdbmoviesapp.di

import com.barryzea.tmdbmoviesapp.model.repository.TmdbDataSource
import com.barryzea.tmdbmoviesapp.model.repository.TmdbDataSourceImpl
import com.barryzea.tmdbmoviesapp.model.repository.TmdbRepository
import com.barryzea.tmdbmoviesapp.model.server.TmdbRetrofitInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 18/05/2022.
 * Copyright (c)  All rights reserved.
 ***/
@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun TmdbRepositoryProvides(
        tmdbRepository : TmdbDataSource,
        @Named("apiKey") apiKey:String,
        tmdbRetrofitService:TmdbRetrofitInterface,
        @Named("language") language:String,
    )=TmdbRepository(tmdbRepository,apiKey, tmdbRetrofitService, language)
}