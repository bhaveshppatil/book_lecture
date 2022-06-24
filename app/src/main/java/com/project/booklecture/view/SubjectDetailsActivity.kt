package com.project.booklecture.view

import android.content.Intent
import android.os.Bundle
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
import com.project.booklecture.adapter.clicklistener.OnItemClick
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
        loadDataFromApi()

        dataBinding.ivSearch.setOnClickListener {
            dataBinding.searchBar.visibility = View.VISIBLE
        }

        dataBinding.ivFilterData.setOnClickListener {
            showToast("Clicked filter icon")
        }

        dataBinding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                dataBinding.searchBar.visibility = View.GONE
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchLectureInList(newText.toString())
                return false
            }
        })
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
                            dataBinding.apply {
                                shimmer.stopShimmer()
                                shimmer.visibility = View.GONE
                                recyclerView.visibility = View.VISIBLE
                                setRecyclerView()
                            }
                        }
                    }
                }
            }
        })
    }

    private fun setRecyclerView(){
        lectureAdapter = LectureAdapter(dataList, this)
        dataBinding.recyclerView.apply {
            adapter = lectureAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun searchLectureInList(query: String) {

        val resultList = ArrayList<ClassResponseItem>()
        for (data in dataList){
            if (data.classname.toLowerCase().contains(query.toLowerCase())) {
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
}