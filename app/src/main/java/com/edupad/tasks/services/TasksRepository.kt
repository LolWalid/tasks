package com.edupad.tasks.services

import android.util.Log
import com.edupad.tasks.models.Task

class TasksRepository {
    private val tasksService = TaskApi.tasksService

    suspend fun deleteTask(id: String): Boolean {
        val tasksResponse = tasksService.deleteTask(id)
        return tasksResponse.isSuccessful
    }

    suspend fun loadTasks(): List<Task>? {
        Log.e("TasksRepository", "loadTasks")
        val tasksResponse = tasksService.getTasks()

        Log.e("TasksRepository", "tasksResponse")
        return if (tasksResponse.isSuccessful) tasksResponse.body() else null
    }
}
