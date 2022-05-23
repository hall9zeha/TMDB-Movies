package com.barryzea.tmdbmoviesapp.viewModel

import com.barryzea.tmdbmoviesapp.model.server.Movie

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 22/05/2022.
 * Copyright (c)  All rights reserved.
 ***/
interface OnClickMovie {
    fun onClick(movie:Movie)
}