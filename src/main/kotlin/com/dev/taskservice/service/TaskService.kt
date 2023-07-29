package com.dev.taskservice.service

import com.dev.taskservice.model.request.task.TaskCreateRequest
import com.dev.taskservice.model.response.task.TaskCreateResponse

interface TaskService {
    fun createTask(taskCreateRequest : TaskCreateRequest): TaskCreateResponse

}