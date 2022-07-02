package com.project.booklecture.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.booklecture.adapter.listeners.OnItemClick
import com.project.booklecture.databinding.LectureDetailsLayoutBinding
import com.project.booklecture.remote.response.ClassResponseItem

class LectureViewHolder(
    private val itemDetailsLayoutBinding: LectureDetailsLayoutBinding,
    private val onItemClick: OnItemClick
) : RecyclerView.ViewHolder(itemDetailsLayoutBinding.root) {

    fun SetData(data: ClassResponseItem) {
        itemDetailsLayoutBinding.apply {
            Glide.with(ivClassLogo).load(data.logo).into(ivClassLogo)
            tvClassName.text = data.classname
            tvField.text = data.field
            tvLectureDateTime.text = "${data.date}, ${data.time}"

            crdLecture.setOnClickListener {
                onItemClick.onLectureClick(data)
            }
        }
    }
}