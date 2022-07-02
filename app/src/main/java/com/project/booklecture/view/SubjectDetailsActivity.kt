package com.project.booklecture.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.booklecture.R
import com.project.booklecture.adapter.LectureAdapter
import com.project.booklecture.adapter.listeners.OnItemClick
import com.project.booklecture.databinding.ActivitySubjectDetailsBinding
import com.project.booklecture.di.viewmodel.LectureViewModel
import com.project.booklecture.remote.Status
import com.project.booklecture.remote.response.ClassResponseItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectDetailsActivity : AppCompatActivity(), OnItemClick {

    private lateinit var dataBinding: ActivitySubjectDetailsBinding
    private lateinit var lectureAdapter: LectureAdapter
    private val lectureViewModel: LectureViewModel by viewModels()
    private val dataList = mutableListOf<ClassResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_subject_details)

        supportActionBar?.hide()
        var intent = Intent()
        intent = getIntent()
        var key = intent.getStringExtra("key")
        if (key?.equals("Physics") == true) {
            dataBinding.toolbar.title = "Physics Classes"
        } else if (key?.equals("Chemistry") == true) {
            dataBinding.toolbar.title = "Chemistry Classes"
        } else if (key?.equals("Mathematics") == true) {
            dataBinding.toolbar.title = "Mathematics Classes"
        } else {
            dataBinding.toolbar.title = "Biology Classes"
        }
        setSupportActionBar(dataBinding.toolbar)
        setRecyclerView()
        loadDataFromApi()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)
        val searchView = menu!!.findItem(R.id.search_bar)
        val searchManager = searchView.actionView as SearchView
        searchManager.queryHint = "Search classes here"
        searchManager.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchLectureInList(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.acClass -> {
                filterAcClasses()
            }
            R.id.nonAcClass -> {

            }
            R.id.lowToHigh -> {

            }
            R.id.search_bar -> {

            }
        }
        return true
    }

    private fun loadDataFromApi() {
        dataBinding.shimmer.startShimmer()
        lectureViewModel.getDataFromApi().observe(this, Observer { it ->

            when (it.status) {
                Status.ERROR -> {

                    dataBinding.apply {
                        shimmer.startShimmer()
                        shimmer.visibility = View.GONE
                        showToast("Network Error")
                    }
                }
                Status.SUCCESS -> {
                    it.data?.let {
                        for (i in it.iterator()) {
                            dataList.addAll(listOf(i))
                            updateUI(listOf(i))
                            dataBinding.apply {
                                shimmer.stopShimmer()
                                shimmer.visibility = View.GONE
                                recyclerView.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        })
    }

    private fun updateUI(dataList: List<ClassResponseItem>) {

        if (dataList.isEmpty()) {
            dataBinding.recyclerView.visibility = View.GONE
            dataBinding.tvEmptyList.visibility = View.VISIBLE
        } else {
            dataBinding.tvEmptyList.visibility = View.GONE
            dataBinding.recyclerView.visibility = View.VISIBLE
        }
    }

    private fun setRecyclerView() {
        lectureAdapter = LectureAdapter(dataList, this)
        dataBinding.recyclerView.apply {
            adapter = lectureAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun searchLectureInList(query: String) {
        val resultList = ArrayList<ClassResponseItem>()
        for (data in dataList) {
            if (data.classname.toLowerCase().contains(query.toLowerCase())) {
                resultList.add(data)
            }
        }
        if (resultList.isEmpty()) {
            dataBinding.apply {
                recyclerView.visibility = View.GONE
                shimmer.visibility = View.GONE
                tvEmptyList.visibility = View.VISIBLE
            }
        } else {
            lectureAdapter.searchLectureInList(resultList)
        }
    }

    private fun filterAcClasses() {
        val resultList = ArrayList<ClassResponseItem>()
        for (data in dataList) {
            if (data.ac_nonac.toLowerCase().contains("ac")) {
                resultList.add(data)
            }
        }
        if (resultList.isEmpty()) {
            showToast("Entered data not found in list")
        } else {
            lectureAdapter.searchLectureInList(resultList)
        }
    }

    private fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    override fun onLectureClick(classResponseItem: ClassResponseItem) {
        val intent = Intent(this, ClassDetailsActivity::class.java)
        intent.putExtra("classResponseItem", classResponseItem)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        dataList.clear()
    }
}