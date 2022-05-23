package com.barryzea.tmdbmoviesapp.viewModel

import com.barryzea.tmdbmoviesapp.model.repository.TmdbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 18/05/2022.
 * Copyright (c)  All rights reserved.
 ***/
@Module
@InstallIn(ViewModelComponent::class)
class MainModuleProvides {
    @Provides
    @ViewModelScoped
    fun getPopularMoviesProvider(repository: TmdbRepository)=GetPopularMoviesTmdb(repository)
}