package com.project.booklecture.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.project.booklecture.R
import com.project.booklecture.databinding.ActivityLiveTopicsBinding

class LiveTopicsActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityLiveTopicsBinding
    private var click : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_live_topics)

        var intent = Intent()
        val extras = getIntent().extras
        var key = extras?.getString("topic")

        if (key?.equals("android") == true) {
            dataBinding.ivEventBanner.setImageResource(R.drawable.android_live)
            dataBinding.tvEventName.text = "Learn to build Native Android Mobile Applications"
        } else if (key?.equals("programming") == true) {
            dataBinding.ivEventBanner.setImageResource(R.drawable.programming_live)
            dataBinding.tvEventName.text = "Oracle Developer Live: Simplifying Modern App Dev"
        } else if (key?.equals("automation") == true) {
            dataBinding.ivEventBanner.setImageResource(R.drawable.programming_live)
            dataBinding.tvEventName.text = "Robocorp RPA Framework"
        } else if (key?.equals("developer") == true) {
            dataBinding.ivEventBanner.setImageResource(R.drawable.java_live)
            dataBinding.tvEventName.text = "Oracle Developer Live: Simplifying Modern App Dev"

        } else if (key?.equals("engineering") == true) {
            dataBinding.ivEventBanner.setImageResource(R.drawable.engineering_live)
            dataBinding.tvEventName.text = "International Conference on Mechanical Engineering"

        } else if (key?.equals("science") == true) {
            dataBinding.ivEventBanner.setImageResource(R.drawable.science_live)
            dataBinding.tvEventName.text = "International Conference on Science Engineering"

        }

        dataBinding.btnRegister.setOnClickListener {
            Toast.makeText(this, "Registered Successfully\n Will Notify you soon...", Toast.LENGTH_SHORT).show()
        }
        dataBinding.ivTermsConditions.setOnClickListener {
            if (click % 2 == 0){
                dataBinding.tvTermConditions.visibility = View.VISIBLE
            }else{
                dataBinding.tvTermConditions.visibility = View.GONE
            }
            click++
        }
    }
}