package com.dev.taskservice.service.impl

import com.dev.taskservice.converter.task.TaskCreateResponseConverter
import com.dev.taskservice.converter.task.TaskEntityConverter
import com.dev.taskservice.error.exception.ResourceAlreadyExistsException
import com.dev.taskservice.error.exception.ResourceNotFoundException
import com.dev.taskservice.model.request.task.TaskCreateRequest
import com.dev.taskservice.model.response.task.TaskCreateResponse
import com.dev.taskservice.repository.TaskRepository
import com.dev.taskservice.repository.UserRepository
import com.dev.taskservice.service.TaskService
import org.springframework.stereotype.Service

@Service
class TaskServiceImpl(val taskRepository: TaskRepository,
                      val userRepository: UserRepository,
                      val taskEntityConverter: TaskEntityConverter,
                      val taskCreateResponseConverter: TaskCreateResponseConverter) : TaskService {
    override fun createTask(taskCreateRequest: TaskCreateRequest): TaskCreateResponse {
        taskRepository.findByTitle(taskCreateRequest.title)
            ?.run { throw ResourceAlreadyExistsException("Task already exists with '${taskCreateRequest.title}' title ") }

        var task = taskEntityConverter.apply(taskCreateRequest)
        val user = userRepository.findByEmail(taskCreateRequest.userEmail) ?: throw ResourceNotFoundException("User not found with '${taskCreateRequest.userEmail}' email")

        task.user = user
        task = taskRepository.save(task)

        return taskCreateResponseConverter.apply(task)
    }

}