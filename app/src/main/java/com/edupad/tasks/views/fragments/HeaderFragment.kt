package com.edupad.tasks.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edupad.tasks.R
import com.edupad.tasks.models.Task
import com.edupad.tasks.models.TaskViewModel
import com.edupad.tasks.views.tasks.TasksAdapter
import kotlinx.android.synthetic.main.main_fragment_layout.view.*

class HeaderFragment: Fragment() {
    private val viewAdapter:TasksAdapter = TasksAdapter(TaskViewModel.tasks) { task : Task -> deleteItem(task) }
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
            adapter = viewAdapter
        }

        return view
    }

    private fun deleteItem(task: Task) {
        val index = TaskViewModel.removeTask(task)
        viewAdapter.notifyItemRemoved(index)
    }
}
