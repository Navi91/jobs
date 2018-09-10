package com.dkrasnov.jobs.ui.adapter

interface RecyclerItemClickListener<T> {

    fun onItemClicked(item: T, position: Int)
}