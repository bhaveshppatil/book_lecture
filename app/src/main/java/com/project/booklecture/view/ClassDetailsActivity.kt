package com.project.booklecture.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.project.booklecture.R
import com.project.booklecture.databinding.ActivityLectureDetailsBinding
import com.project.booklecture.remote.response.ClassResponseItem

class ClassDetailsActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityLectureDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_lecture_details)

        var intent = Intent()
        intent = getIntent()

        val item = intent.getSerializableExtra("classResponseItem") as ClassResponseItem

        dataBinding.apply {
            tvCoachingPlaceName.text = item.classname
            tvACNonAc.text = item.ac_nonac
            tvDateTime.text = "${item.date} ${item.time}"
            tvClassSize.text = "Size- ${item.size}"
            tvContact.text = item.contact
            tvLanguage.text = item.language
            tvAddress.text = item.address
            tvSeatAvailability.text = "Seat- ${item.density}"
            tvFees.text = "₹${item.price}"
            Glide.with(ivClassPhotos).load(item.images).into(ivClassPhotos)
        }

        dataBinding.btnBookSeat.setOnClickListener {
            val intent = Intent(this, SheetContainerActivity::class.java)
            intent.putExtra("classResponseItem", item)
            startActivity(intent)
        }
    }
}