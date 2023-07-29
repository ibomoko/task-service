package com.dev.taskservice.converter.task

import com.dev.taskservice.entity.Task
import com.dev.taskservice.model.request.task.TaskCreateRequest
import org.springframework.stereotype.Component
import java.util.Calendar
import java.util.Date
import java.util.function.Function

@Component
class TaskEntityConverter: Function<TaskCreateRequest, Task> {
    override fun apply(request: TaskCreateRequest): Task {
        val dueDate = request.dueDate ?: addOneWeekToCurrentDate()

        return Task(null,
            request.title,
            request.description,
            dueDate,
            false,
            Date(),
            null,
            null,
            false)
    }

    private fun addOneWeekToCurrentDate(): Date {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_YEAR, 7)
        return currentDate.time
    }
}