package com.project.booklecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.project.booklecture.adapter.ViewPagerAdapter
import com.project.booklecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init(){
        dataBinding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(dataBinding.tabDots, dataBinding.viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->

        }).attach()
        dataBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){

        })
    }

}