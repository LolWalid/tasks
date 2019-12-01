package com.edupad.tasks.models

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.edupad.tasks.services.TasksRepository
import com.edupad.tasks.views.tasks.TasksAdapter

class TasksViewModel: ViewModel() {
    private val tasks = mutableListOf<Task>()
    val tasksAdapter= TasksAdapter(tasks) { task : Task -> deleteItem(task) }

    private val tasksRepository = TasksRepository()

    fun loadTasks(lifecycleOwner: LifecycleOwner): List<Task>? {
        tasksRepository.getTasks().observe(lifecycleOwner, Observer {
            Log.e("it", it.toString())
            if (it != null) {
                tasks.clear()
                tasks.addAll(it)
                tasksAdapter.notifyDataSetChanged()
            }
        })
        return tasks
    }

    private fun deleteItem(task: Task) {
        val index = removeTask(task)
        tasksAdapter.notifyItemRemoved(index)
    }

    private fun removeTask(task: Task): Int {
        val index = tasks.indexOf(task)
        tasks.remove(task)
        return index
    }
}
