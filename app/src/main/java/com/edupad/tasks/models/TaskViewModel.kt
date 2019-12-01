package com.edupad.tasks.models

object TaskViewModel {
    val tasks = mutableListOf(
        Task(id = "id_1", title = "Task 1", description = "description 1"),
        Task(id = "id_2", title = "Task 2"),
        Task(id="id_3", title = "Task 3")
    )

    fun removeTask(task: Task): Int {
        val index = tasks.indexOf(task)
        tasks.remove(task)
        return index
    }
}
