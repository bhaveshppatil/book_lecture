package com.project.booklecture.view

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.project.booklecture.R
import com.project.booklecture.databinding.ActivityPaymentDetailsBinding
import com.project.booklecture.remote.response.ClassResponseItem

class PaymentDetails : AppCompatActivity() {

    private lateinit var dataBinding: ActivityPaymentDetailsBinding
    private lateinit var data: ClassResponseItem
    private var seatQTY: Int = 0
    private lateinit var uri: Uri
    private val RESULT_CODE: Int = 99
    private lateinit var status: String
    private val GPAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment_details)

        var intent = Intent()
        intent = getIntent()
        data = intent.getSerializableExtra("classResponseItem") as ClassResponseItem
        seatQTY = intent.getIntExtra("seatQTY", 0)

        dataBinding.apply {
            tvClassName.text = "Class Name- ${data.classname}"
            tvClassDate.text = "Date- ${data.date}"
            tvClassFees.text = "Total Fees- ${data.price * seatQTY}"
            tvSeatBooked.text = "Seat- $seatQTY"

            btnProceed.setOnClickListener {
                uri = getUpiPaymentUri()
                payWithGPAY(GPAY_PACKAGE_NAME)
            }
        }
    }

    private fun getUpiPaymentUri(): Uri {
        return Uri.Builder()
            .scheme("upi")
            .authority("pay")
            .appendQueryParameter("pa", "bhavesh.patil0325-2@okhdfcbank")
            .appendQueryParameter("pn", "Bhavesh P Patil")
            .appendQueryParameter("tn", "Registration Fees")
            .appendQueryParameter("am", "1")
            .appendQueryParameter("cu", "INR")
            .build()
    }

    private fun payWithGPAY(packageName: String) {
        if (isGPayInstalled(this, packageName)) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            intent.setPackage(packageName)
            startActivityForResult(intent, 0)
        } else {
            Toast.makeText(
                this,
                "Google Pay is Not Installed. Install it & Try again",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun isGPayInstalled(context: Context, packageName: String): Boolean {
        return try {
            context.packageManager.getApplicationInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            status = data.getStringExtra("status").toString()
        }
        if (RESULT_CODE == resultCode && status.equals("success")) {
            Toast.makeText(this, "Transaction Successful", Toast.LENGTH_SHORT).show()
            dataBinding.tvPaymentStatus.text = "Transaction Successful"
            dataBinding.tvPaymentStatus.setTextColor(Color.BLACK)
        } else {
            Toast.makeText(this, "Transaction Failed", Toast.LENGTH_SHORT).show()
            dataBinding.tvPaymentStatus.text = "Transaction Failed"
            dataBinding.tvPaymentStatus.setTextColor(Color.GRAY)
        }
    }
}