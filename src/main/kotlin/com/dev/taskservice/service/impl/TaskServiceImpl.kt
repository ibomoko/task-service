package com.dev.taskservice.service.impl

import com.dev.taskservice.converter.task.TaskCreateResponseConverter
import com.dev.taskservice.converter.task.TaskEntityConverter
import com.dev.taskservice.converter.task.TaskResponseConverter
import com.dev.taskservice.entity.Task
import com.dev.taskservice.error.exception.ResourceAlreadyExistsException
import com.dev.taskservice.error.exception.ResourceNotFoundException
import com.dev.taskservice.model.request.task.TaskCreateRequest
import com.dev.taskservice.model.request.task.TaskFilter
import com.dev.taskservice.model.response.task.TaskCreateResponse
import com.dev.taskservice.model.response.task.TaskResponse
import com.dev.taskservice.repository.TaskRepository
import com.dev.taskservice.repository.UserRepository
import com.dev.taskservice.service.TaskService
import com.dev.taskservice.specification.TaskSpecification
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TaskServiceImpl(val taskRepository: TaskRepository,
                      val userRepository: UserRepository,
                      val taskEntityConverter: TaskEntityConverter,
                      val taskCreateResponseConverter: TaskCreateResponseConverter,
                      val taskResponseConverter: TaskResponseConverter) : TaskService {
    override fun createTask(taskCreateRequest: TaskCreateRequest): TaskCreateResponse {
        taskRepository.findByTitle(taskCreateRequest.title)
            ?.run { throw ResourceAlreadyExistsException("Task already exists with '${taskCreateRequest.title}' title ") }

        var task = taskEntityConverter.apply(taskCreateRequest)
        val user = userRepository.findByEmail(taskCreateRequest.userEmail) ?: throw ResourceNotFoundException("User not found with '${taskCreateRequest.userEmail}' email")

        task.user = user
        task = taskRepository.save(task)

        return taskCreateResponseConverter.apply(task)
    }

    override fun getTaskPage(taskFilter: TaskFilter, pageable: Pageable): Page<TaskResponse> {
        val taskSpecification = TaskSpecification(taskFilter)
        val taskPage = taskRepository.findAll(taskSpecification, pageable)

        return taskPage.map { task -> taskResponseConverter.apply(task) }
    }

}