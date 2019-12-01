package com.edupad.tasks.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edupad.tasks.models.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TasksRepository {
    private val tasksService = TaskApi.tasksService

    suspend fun deleteTask(id: String): Boolean {
        val tasksResponse = tasksService.deleteTask(id)
        return tasksResponse.isSuccessful
    }

    fun getTasks(): LiveData<List<Task>?> {
        val tasks =  MutableLiveData<List<Task>?>()
        GlobalScope.launch {
            tasks.postValue(loadTasks())
        }
        return tasks
    }

    private suspend fun loadTasks(): List<Task>? {
        val tasksResponse = tasksService.getTasks()
        return if (tasksResponse.isSuccessful) tasksResponse.body() else null
    }
}
