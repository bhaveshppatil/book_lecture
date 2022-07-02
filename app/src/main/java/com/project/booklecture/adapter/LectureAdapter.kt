package com.project.booklecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.booklecture.R
import com.project.booklecture.adapter.listeners.OnItemClick
import com.project.booklecture.adapter.viewHolder.LectureViewHolder
import com.project.booklecture.databinding.LectureDetailsLayoutBinding
import com.project.booklecture.remote.response.ClassResponseItem

class LectureAdapter(
    var dataModelList: MutableList<ClassResponseItem>,
    val onItemClick: OnItemClick
): RecyclerView.Adapter<LectureViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureViewHolder {
        val itemLayoutBinding : LectureDetailsLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.lecture_details_layout,
            parent,
            false
        )
        return LectureViewHolder(itemLayoutBinding, onItemClick)
    }

    override fun onBindViewHolder(holder: LectureViewHolder, position: Int) {
        val dataModel = dataModelList[position]
        holder.SetData(dataModel)
    }

    override fun getItemCount(): Int {
        return dataModelList.size
    }

    fun searchLectureInList(searchLectureList: ArrayList<ClassResponseItem>) {
        dataModelList = searchLectureList
        notifyDataSetChanged()
    }
}