package com.barryzea.tmdbmoviesapp.ui.common

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 21/05/2022.
 * Copyright (c)  All rights reserved.
 ***/

class SingleMutableLiveData<T>: MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer { t->
            if(t!=null){
                observer.onChanged(t)
                /*
                * limpiamos el valor de la colección que devuelva el observador
                * para que no nos devuelva la ultima instancia guardada, en mi caso lo necesito así
                * */
                postValue(null)
            }
        })

    }
}