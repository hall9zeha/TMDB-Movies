package com.barryzea.tmdbmoviesapp.ui.common

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.barryzea.tmdbmoviesapp.model.server.Movie
import com.google.android.material.textview.MaterialTextView

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 22/05/2022.
 * Copyright (c)  All rights reserved.
 ***/

class MovieDetailInfo @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): MaterialTextView(context,attrs,defStyleAttr) {
    fun setMovieInfo(movie:Movie)=with(movie){
        text= buildSpannedString {
            bold{append("Original language: ")}
            appendLine(originalLanguage)

            bold{append("Original title: ")}
            appendLine(originalTitle)

            bold{append("Release date: ")}
            appendLine(releaseDate)

            bold{append("Popularity: ")}
            appendLine(popularity.toString())

            bold{append("Vote average: ")}
            appendLine(voteAverage.toString())
        }
    }
}