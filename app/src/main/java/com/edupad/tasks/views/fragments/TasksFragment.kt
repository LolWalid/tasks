package com.edupad.tasks.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edupad.tasks.R
import com.edupad.tasks.models.TasksViewModel
import com.edupad.tasks.services.TaskApi
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

    override fun onResume() {
        super.onResume()
        fetchTasks()
        fetchUserInfo()
    }

    private fun fetchTasks() {
        tasksViewModel.loadTasks(this)
    }

    private fun fetchUserInfo() {
        GlobalScope.launch {
            TaskApi.userService.getInfo()
        }
    }
}
