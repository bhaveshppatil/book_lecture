package com.project.booklecture.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.project.booklecture.R
import com.project.booklecture.adapter.ViewPagerAdapter
import com.project.booklecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()

        dataBinding.apply {
            ivMathSub.setOnClickListener(this@MainActivity)
            ivPhysicsSub.setOnClickListener(this@MainActivity)
            ivBiologySub.setOnClickListener(this@MainActivity)
            ivChemistrySub.setOnClickListener(this@MainActivity)
            ivCs.setOnClickListener(this@MainActivity)
            ivEnglish.setOnClickListener(this@MainActivity)
            androidLive.setOnClickListener(this@MainActivity)
            automationLive.setOnClickListener(this@MainActivity)
            developerLive.setOnClickListener(this@MainActivity)
            engineeringLive.setOnClickListener(this@MainActivity)
            programmingLive.setOnClickListener(this@MainActivity)
            scienceLive.setOnClickListener(this@MainActivity)
            developersEvent.setOnClickListener(this@MainActivity)
            electronicsEvent.setOnClickListener(this@MainActivity)
            engineeringEvent.setOnClickListener(this@MainActivity)
            roboticsEvent.setOnClickListener(this@MainActivity)
            programmingEvent.setOnClickListener(this@MainActivity)
            techEvent.setOnClickListener(this@MainActivity)
            technologyOST.setOnClickListener(this@MainActivity)
            roboticsOST.setOnClickListener(this@MainActivity)
            csOST.setOnClickListener(this@MainActivity)
            automationOST.setOnClickListener(this@MainActivity)
            androidOST.setOnClickListener(this@MainActivity)
        }
    }

    private fun init() {
        dataBinding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(
            dataBinding.tabDots,
            dataBinding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { _, _ ->

            }).attach()
        dataBinding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivPhysicsSub -> {
                val intent = Intent(this@MainActivity, SubjectDetailsActivity::class.java)
                intent.putExtra("key","Physics")
                startActivity(intent)
            }
            R.id.ivChemistrySub -> {
                val intent = Intent(this@MainActivity, SubjectDetailsActivity::class.java)
                intent.putExtra("key","Chemistry")
                startActivity(intent)
            }
            R.id.ivMathSub -> {
                val intent = Intent(this@MainActivity, SubjectDetailsActivity::class.java)
                intent.putExtra("key","Mathematics")
                startActivity(intent)
            }
            R.id.ivBiologySub -> {
                val intent = Intent(this@MainActivity, SubjectDetailsActivity::class.java)
                intent.putExtra("key","Biology")
                startActivity(intent)
            }
            R.id.androidLive -> {
                val intent = Intent(this, LiveTopicsActivity::class.java)
                intent.putExtra("topic", "android")
                startActivity(intent)
            }
            R.id.automationLive -> {
                val intent = Intent(this, LiveTopicsActivity::class.java)
                intent.putExtra("topic", "automation")
                startActivity(intent)
            }
            R.id.developerLive -> {
                val intent = Intent(this, LiveTopicsActivity::class.java)
                intent.putExtra("topic", "developer")

                startActivity(intent)
            }
            R.id.engineeringLive -> {
                val intent = Intent(this, LiveTopicsActivity::class.java)
                intent.putExtra("topic", "engineering")

                startActivity(intent)
            }
            R.id.scienceLive -> {
                val intent = Intent(this, LiveTopicsActivity::class.java)
                intent.putExtra("topic", "science")

                startActivity(intent)
            }
            R.id.programmingLive -> {
                val intent = Intent(this, LiveTopicsActivity::class.java)
                intent.putExtra("topic", "programming")

                startActivity(intent)
            }
        }
    }
}