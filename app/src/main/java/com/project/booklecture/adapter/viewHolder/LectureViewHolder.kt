package com.project.booklecture.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.booklecture.adapter.clicklistener.OnItemClick
import com.project.booklecture.databinding.LectureDetailsLayoutBinding
import com.project.booklecture.remote.response.Product

class LectureViewHolder(
    private val itemDetailsLayoutBinding: LectureDetailsLayoutBinding,
    private val onItemClick: OnItemClick
) : RecyclerView.ViewHolder(itemDetailsLayoutBinding.root) {

    fun SetData(product: Product){
        itemDetailsLayoutBinding.apply {
            Glide.with(ivClassLogo).load(product.thumbnail).into(ivClassLogo)
            tvClassName.text = product.title
            tvClassLocation.text = product.category
            tvLectureDateTime.text = "${product.discountPercentage} ${product.price}"

            crdLecture.setOnClickListener {
                onItemClick.onLectureClick(product)
            }
        }
    }
}