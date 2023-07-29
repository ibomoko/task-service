package com.dev.taskservice.converter.task

import com.dev.taskservice.entity.Task
import com.dev.taskservice.model.response.task.TaskCreateResponse
import org.springframework.stereotype.Component
import java.util.function.Function

@Component
class TaskCreateResponseConverter : Function<Task, TaskCreateResponse> {
    override fun apply(task: Task): TaskCreateResponse {
        return TaskCreateResponse(
            task.title!!,
            task.description!!,
            task.dueDate!!,
            task.createDate!!,
            task.isCompleted!!,
            task.user!!.id!!,
            task.isDeleted!!)
    }
}