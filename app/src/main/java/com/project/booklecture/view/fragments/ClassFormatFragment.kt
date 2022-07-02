package com.project.booklecture.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.project.booklecture.R
import com.project.booklecture.adapter.listeners.FragCommunications
import com.project.booklecture.databinding.FragmentClassFormatBinding

class ClassFormatFragment : Fragment(R.layout.fragment_class_format) {
    private lateinit var dataBinding: FragmentClassFormatBinding
    private lateinit var communications: FragCommunications

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_class_format, container, false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.apply {

            rgSelectFormat.setOnCheckedChangeListener { _, _ ->
                val radioButton: Int = rgSelectFormat.checkedRadioButtonId
                if (rBtnAcClass.isChecked){
                    showToast("Ac class selected")
                }else{
                    showToast("Non Ac class selected")
                }
            }

            rgSelectLanguage.setOnCheckedChangeListener { _, _ ->
                if (rBtnEngLng.isChecked){
                    showToast("English language selected")
                }else{
                    showToast("Hindi language selected")
                }
            }

            btnNext.setOnClickListener {
                if (rBtnAcClass.isChecked || rBtnNonAcClass.isChecked &&
                    rBtnEngLng.isChecked || rBtnHindiLng.isChecked){
                        val bundle = Bundle()
                    communications.launchSeatBookingFrag(bundle)
                }else{
                    showToast("Select Language & Format both")
                }
            }
        }
    }

    private fun showToast(str: String) {
        Toast.makeText(context, str,Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communications = context as FragCommunications
    }
}