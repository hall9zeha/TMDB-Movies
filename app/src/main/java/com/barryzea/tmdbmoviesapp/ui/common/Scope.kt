package com.barryzea.tmdbmoviesapp.ui.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 21/05/2022.
 * Copyright (c)  All rights reserved.
 ***/

interface Scope:CoroutineScope {

    class Impl:Scope{
        override lateinit var job:Job
    }

    var job:Job
    override val coroutineContext: CoroutineContext
        get() =Dispatchers.Main + job

    fun initScope(){
        job= SupervisorJob()
    }
    fun destroyScope(){
        job.cancel()
    }
}