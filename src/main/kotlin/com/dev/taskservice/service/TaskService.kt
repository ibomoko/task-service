package com.dev.taskservice.service

import com.dev.taskservice.model.request.task.TaskCreateRequest
import com.dev.taskservice.model.request.task.TaskFilter
import com.dev.taskservice.model.response.task.TaskCreateResponse
import com.dev.taskservice.model.response.task.TaskResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TaskService {
    fun createTask(taskCreateRequest : TaskCreateRequest): TaskCreateResponse
    fun getTaskPage(taskFilter: TaskFilter, pageable: Pageable) : Page<TaskResponse>

}