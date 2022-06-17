package com.project.booklecture

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.project.booklecture.adapter.ViewPagerAdapter
import com.project.booklecture.databinding.ActivityMainBinding
import com.project.booklecture.view.SubjectDetailsActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()

        dataBinding.apply {

            ivMathSub.setOnClickListener { this@MainActivity }
            ivChemistrySub.setOnClickListener { this@MainActivity }
            ivBiologySub.setOnClickListener { this@MainActivity }
            ivPhysicsSub.setOnClickListener { this@MainActivity }
        }
    }

    private fun init() {
        dataBinding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(
            dataBinding.tabDots,
            dataBinding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->

            }).attach()
        dataBinding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivPhysicsSub, R.id.ivChemistrySub, R.id.ivMathSub, R.id.ivBiologySub -> {
                val intent = Intent(this@MainActivity, SubjectDetailsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}