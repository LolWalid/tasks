package com.edupad.tasks.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edupad.tasks.services.TasksRepository
import com.edupad.tasks.views.tasks.TasksAdapter
import kotlinx.coroutines.launch

class TasksViewModel: ViewModel() {
    private val tasks = mutableListOf<Task>()
    val tasksAdapter= TasksAdapter(tasks) { task : Task -> deleteItem(task) }

    private val tasksRepository = TasksRepository()

    fun loadTasks() {
        Log.e("TasksViewModel", "loadTasks")
        viewModelScope.launch {
            val serverTasks = tasksRepository.loadTasks()
            Log.e("TasksViewModel", serverTasks.toString())
            serverTasks?.let {
                tasks.clear()
                tasks.addAll(it)
                tasksAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun deleteItem(task: Task) {
        viewModelScope.launch {
            val success = tasksRepository.deleteTask(task.id)
            if (success) {
                val index = removeTask(task)
                tasksAdapter.notifyItemRemoved(index)
            }
        }
    }

    private fun removeTask(task: Task): Int {
        val index = tasks.indexOf(task)
        tasks.remove(task)
        return index
    }
}
