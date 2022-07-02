package com.project.booklecture.view

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.project.booklecture.R
import com.project.booklecture.adapter.listeners.FragCommunications
import com.project.booklecture.remote.response.ClassResponseItem
import com.project.booklecture.view.fragments.ClassFormatFragment
import com.project.booklecture.view.fragments.SeatBookingFragment


class SheetContainerActivity : AppCompatActivity(), FragCommunications {
    private lateinit var frameLayout: FrameLayout
    private lateinit var fragmentManager: FragmentManager
    private lateinit var data : ClassResponseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sheet_container)
        var intent = Intent()
        intent = getIntent()
        data = intent.getSerializableExtra("classResponseItem") as ClassResponseItem
        frameLayout = findViewById(R.id.sheet_container_frag)
        launchDateTimeFrag()
    }

    private fun launchDateTimeFrag() {
        fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val classFormatFragment = ClassFormatFragment()
        fragmentTransaction.replace(
            R.id.sheet_container_frag,
            classFormatFragment,
            "classFormatFragment"
        ).commit()
    }

    override fun launchSeatBookingFrag(bundle: Bundle) {
        fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val seatBookingFragment = SeatBookingFragment()
        bundle.putSerializable("classResponseItem", data)
        seatBookingFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.sheet_container_frag,
            seatBookingFragment,
            "seatBookingFragment"
        ).commit()
    }
}