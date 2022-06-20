package com.project.booklecture.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.project.booklecture.R
import com.project.booklecture.databinding.ActivityLectureDetailsBinding
import com.project.booklecture.databinding.LectureDetailsLayoutBinding

class LectureDetailsActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityLectureDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_lecture_details)

        dataBinding.btnBookSeat.setOnClickListener {
            val dialog = BottomSheetDialog(this)

            val view = layoutInflater.inflate(R.layout.seat_booking_sheet, null)

            val radioGroup = findViewById<RadioGroup>(R.id.rgSelectFormat)
            val rBtnAcClass = findViewById<RadioButton>(R.id.rBtnAcClass)
            val rBtnNonAcClass = findViewById<RadioButton>(R.id.rBtnNonAcClass)

            radioGroup?.setOnCheckedChangeListener { _, _ ->
                val radioButton: Int = radioGroup.checkedRadioButtonId

                if (rBtnAcClass.id == radioButton) {
                    showToast("Ac Class Selected")
                }else if (rBtnNonAcClass.id == radioButton) {
                    showToast("Non Ac Class Selected")
                }else{
                    showToast("Please select format ")
                }
            }

            val radioGroupLng = findViewById<RadioGroup>(R.id.rgSelectLanguage)
            val rBtnHindiLng = findViewById<RadioButton>(R.id.rBtnHindiLng)
            val rBtnEngLng = findViewById<RadioButton>(R.id.rBtnEngLng)

            radioGroupLng?.setOnCheckedChangeListener { _, _ ->
                val radioButton: Int = radioGroup.checkedRadioButtonId

                if (rBtnHindiLng.id == radioButton) {
                    showToast("Hindi Language Selected")
                }else if (rBtnEngLng.id == radioButton) {
                    showToast("English Language Selected")

                }else{
                    showToast("Please select Language ")
                }
            }

            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }

        dataBinding.btnRateNow.setOnClickListener {
            showToast("Thank You Rating...")
        }
    }
    private fun showToast(data : String){
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }
}