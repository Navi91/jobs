package com.dkrasnov.jobs.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.dkrasnov.jobs.R
import com.dkrasnov.jobs.api.glide.GlideApp
import com.dkrasnov.jobs.model.Vocation
import kotlinx.android.synthetic.main.v_vocation_item.view.*

class VocationsAdapter(private val listener: RecyclerItemClickListener<Vocation>) : RecyclerView.Adapter<VocationsAdapter.VocationItemViewHolder>() {

    var items = listOf<Vocation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocationItemViewHolder {
        return VocationItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.v_vocation_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VocationItemViewHolder, position: Int) {
        val item = items[position]
        val view = holder.itemView

        GlideApp.with(view)
                .load(item.companyLogo)
                .placeholder(R.drawable.ic_image_gray_24dp)
                .downsample(DownsampleStrategy.AT_MOST)
                .into(view.logoImageView)

        view.titleTextView.text = item.title
        view.locationTextView.text = item.location

        view.setOnClickListener { listener.onItemClicked(item, position) }
    }

    class VocationItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}