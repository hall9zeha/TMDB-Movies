package com.barryzea.tmdbmoviesapp.ui.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 21/05/2022.
 * Copyright (c)  All rights reserved.
 ***/

abstract class ScopedViewModel:ViewModel(), Scope by Scope.Impl() {
    init{
        initScope()
    }
    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}