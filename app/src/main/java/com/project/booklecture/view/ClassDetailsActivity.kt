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
            Glide.with(ivClassPhotos).load(item.images).into(ivClassPhotos)
        }

        dataBinding.btnBookSeat.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.seat_booking_sheet, null)
            val radioGroup = findViewById<RadioGroup>(R.id.rgSelectFormat)
            val rBtnAcClass = findViewById<RadioButton>(R.id.rBtnAcClass)
            val rBtnNonAcClass = findViewById<RadioButton>(R.id.rBtnNonAcClass)
            val radioGroupLng = findViewById<RadioGroup>(R.id.rgSelectLanguage)
            val rBtnHindiLng = findViewById<RadioButton>(R.id.rBtnHindiLng)
            val rBtnEngLng = findViewById<RadioButton>(R.id.rBtnEngLng)
            val btnNext = findViewById<Button>(R.id.btnNext)
            val next = view.findViewById<Button>(R.id.btnNext)

            radioGroup?.setOnCheckedChangeListener { _, _ ->
                val radioButton: Int = radioGroup.checkedRadioButtonId
                if (rBtnAcClass.id == radioButton) {
                    showToast("Ac Class Selected")
                }
                if (rBtnNonAcClass.id == radioButton) {
                    showToast("Non Ac Class Selected")
                }
            }

            radioGroupLng?.setOnCheckedChangeListener { _, _ ->
                val radioButton: Int = radioGroup.checkedRadioButtonId
                if (rBtnHindiLng.id == radioButton) {
                    showToast("Hindi Language Selected")
                }
                if (rBtnEngLng.id == radioButton) {
                    showToast("English Language Selected")
                }
            }

            next?.setOnClickListener {
                val intent = Intent(this, PaymentActivity::class.java)
                startActivity(intent)
            }

            dialog.setCancelable(true)
            dialog.setContentView(view)
            dialog.show()

        }

    }
    private fun showToast(data : String){
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }
}