package com.project.booklecture.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.project.booklecture.R
import com.project.booklecture.databinding.FragmentSeatBookingBinding
import com.project.booklecture.remote.response.ClassResponseItem
import com.project.booklecture.view.PaymentDetails

class SeatBookingFragment : Fragment() {

    private lateinit var dataBinding: FragmentSeatBookingBinding
    private var click: Int = 0
    private lateinit var data: ClassResponseItem
    private var selectedSeat: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_seat_booking,
            container,
            false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val data = bundle?.getSerializable("classResponseItem") as ClassResponseItem

        dataBinding.apply {

            tvClassNameSeat.text = data.classname
            tvSeatPrice.text = "${data.price}"

            rgSeat.setOnCheckedChangeListener { _, checkedId ->

                when (checkedId) {
                    R.id.seat1 -> {
                        ivVehicle.setImageResource(R.drawable.bicycle)
                        selectedSeat = 1
                    }
                    R.id.seat2 -> {
                        ivVehicle.setImageResource(R.drawable.motorcycle)
                        selectedSeat = 2
                    }
                    R.id.seat3 -> {
                        ivVehicle.setImageResource(R.drawable.rickshaw)
                        selectedSeat = 3
                    }
                    R.id.seat4 -> {
                        ivVehicle.setImageResource(R.drawable.baby_car)
                        selectedSeat = 4
                    }
                    R.id.seat5 -> {
                        ivVehicle.setImageResource(R.drawable.sedan)
                        selectedSeat = 5
                    }
                    R.id.seat6 -> {
                        ivVehicle.setImageResource(R.drawable.bus)
                        selectedSeat = 6
                    }
                    R.id.seat7 -> {
                        ivVehicle.setImageResource(R.drawable.bus)
                        selectedSeat = 7
                    }
                }
            }
            btnNextPayment.setOnClickListener {
                if (selectedSeat != 0) {
                    val intent = Intent(context, PaymentDetails::class.java)
                    intent.putExtra("classResponseItem", data)
                    intent.putExtra("seatQTY", selectedSeat)
                    startActivity(intent)
                } else {
                    Toast.makeText(context, "Please select seat quantity", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }
}