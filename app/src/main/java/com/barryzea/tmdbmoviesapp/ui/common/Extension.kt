package com.barryzea.tmdbmoviesapp.ui.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.barryzea.tmdbmoviesapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 22/05/2022.
 * Copyright (c)  All rights reserved.
 ***/
inline fun <reified T : Activity> Context.intentFor(body: Intent.() -> Unit): Intent =
    Intent(this, T::class.java).apply(body)

inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(intentFor<T>(body))
}
fun ImageView.loadUrl(url:String){
    Glide.with(context).load(url)
        .placeholder(R.drawable.placeholder_movie)
        .error(R.drawable.placeholder_movie)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}