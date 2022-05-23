package com.barryzea.tmdbmoviesapp.ui.common

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

/****
 * Project TmdbMoviesApp
 * Created by Barry Zea H. on 19/05/2022.
 * Copyright (c)  All rights reserved.
 ***/

abstract class PaginationRecyclerView(linearLayoutManager:GridLayoutManager, private val fab:FloatingActionButton): RecyclerView.OnScrollListener() {
    private  var linearLayoutManager= linearLayoutManager
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if(dy<0 && !fab.isShown){
            fab.show()
        }
        else if(dy>0 && fab.isShown){
            fab.hide()
        }
        val visibleItemCount= linearLayoutManager.childCount
        val totalItemCount= linearLayoutManager.itemCount
        val firstVisibleItemCount= linearLayoutManager.findFirstVisibleItemPosition()

        if(!isLoading()&&!isLastPage()){
            if((visibleItemCount  + firstVisibleItemCount)>=totalItemCount - 5 && firstVisibleItemCount >=0
            )
            {

                loadMoreItems()

            }
        }

    }
    protected abstract fun loadMoreItems()


    abstract fun getTotalPageCount():Int
    abstract fun isLastPage():Boolean
    abstract fun isLoading():Boolean


}