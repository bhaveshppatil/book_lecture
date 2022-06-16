package com.project.booklecture.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.booklecture.R

class ViewPagerAdapter (var context: Context) : RecyclerView.Adapter<AdViewHolder>(){

    var adBannersList = arrayOf<IntArray>(
        intArrayOf(android.R.color.holo_blue_dark, R.drawable.coursera),
        intArrayOf(android.R.color.holo_blue_dark, R.drawable.edx),
        intArrayOf(android.R.color.holo_blue_dark, R.drawable.udemy),
        intArrayOf(android.R.color.holo_blue_dark, R.drawable.simplilearn),
        )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        return AdViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        holder.ivAdBanner.setImageResource(adBannersList[position][1])

    }

    override fun getItemCount(): Int {
        return adBannersList.size
    }

}