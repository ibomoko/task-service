package com.dev.taskservice.converter.task

import com.dev.taskservice.entity.Task
import com.dev.taskservice.model.response.task.TaskResponse
import org.springframework.stereotype.Component
import java.util.function.Function

@Component
class TaskResponseConverter: Function<Task, TaskResponse> {
    override fun apply(task: Task): TaskResponse {
        return TaskResponse(task.id,
            task.title,
            task.description,
            task.createDate,
            task.dueDate,
            task.isCompleted,
            task.isDeleted)
    }
}