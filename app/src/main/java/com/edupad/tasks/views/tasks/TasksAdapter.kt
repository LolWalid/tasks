package com.edupad.tasks.views.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edupad.tasks.R
import com.edupad.tasks.models.Task
import kotlinx.android.synthetic.main.task_view_holder.view.*

class TasksAdapter(private val tasks: MutableList<Task>, private val onDeleteClickListener: (Task) -> Unit) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_view_holder, parent, false)

        return TaskViewHolder(view)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.setup(tasks[position], onDeleteClickListener)
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setup(task: Task, onDeleteClickListener: (Task) -> Unit) {
            itemView.task_title.text = task.title
            itemView.delete_button.setOnClickListener { onDeleteClickListener(task)}
        }
    }
}
