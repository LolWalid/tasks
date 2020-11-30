package com.edupad.tasks.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edupad.tasks.R
import com.edupad.tasks.models.TasksViewModel
import com.edupad.tasks.models.UserInfo
import com.edupad.tasks.services.Api
import kotlinx.android.synthetic.main.main_fragment_layout.*
import kotlinx.android.synthetic.main.main_fragment_layout.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TasksFragment: Fragment() {
    private val tasksViewModel by lazy {
        ViewModelProviders.of(this).get(TasksViewModel::class.java)
    }

    private val viewManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.main_fragment_layout, container, false)
        view.tasks_recycler_view.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = tasksViewModel.tasksAdapter
        }

        return view
    }

    private fun downloadImage() {
        getInfo().observe(this, Observer {
            Glide.with(this).load(it.avatar).into(image_view)
        })
    }

    private fun getInfo(): LiveData<UserInfo> {
        val userInfo = MutableLiveData<UserInfo>()
        GlobalScope.launch {
            val response = Api.INSTANCE.userService.getInfo()
            Log.e("downloadImage", response.body().toString())
            if (response.isSuccessful) {
                response.body()?.let {
                    userInfo.postValue(it)
//                    val u = UserInfo(it.email, it.firstname + "1", it.lastname, it.avatar)
//                    val response2 = Api.INSTANCE.userService.update(u)
                }
            }

        }
        return userInfo
    }

    override fun onResume() {
        super.onResume()
        fetchTasks()
        downloadImage()
    }

    private fun fetchTasks() {
        tasksViewModel.loadTasks()
    }
}
