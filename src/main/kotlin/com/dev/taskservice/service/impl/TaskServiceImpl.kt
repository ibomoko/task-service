package com.dev.taskservice.service.impl

import com.dev.taskservice.converter.task.TaskCreateResponseConverter
import com.dev.taskservice.converter.task.TaskEntityConverter
import com.dev.taskservice.converter.task.TaskResponseConverter
import com.dev.taskservice.error.exception.ResourceAlreadyExistsException
import com.dev.taskservice.error.exception.ResourceNotFoundException
import com.dev.taskservice.error.exception.TaskAlreadyCompletedException
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
import java.security.Principal
import javax.annotation.Resource

@Service
class TaskServiceImpl(val taskRepository: TaskRepository,
                      val userRepository: UserRepository,
                      val taskEntityConverter: TaskEntityConverter,
                      val taskCreateResponseConverter: TaskCreateResponseConverter,
                      val taskResponseConverter: TaskResponseConverter) : TaskService {
    override fun createTask(taskCreateRequest: TaskCreateRequest, principal: Principal): TaskCreateResponse {
        taskRepository.findByTitle(taskCreateRequest.title)
                ?.run { throw ResourceAlreadyExistsException("Task already exists with '${taskCreateRequest.title}' title ") }

        var task = taskEntityConverter.apply(taskCreateRequest)
        val user = userRepository.findByEmail(principal.name) ?: throw ResourceNotFoundException("User not found with '${principal.name}' email")

        task.user = user
        task = taskRepository.save(task)

        return taskCreateResponseConverter.apply(task)
    }

    override fun getTaskPage(taskFilter: TaskFilter, pageable: Pageable): Page<TaskResponse> {
        val taskSpecification = TaskSpecification(taskFilter)
        val taskPage = taskRepository.findAll(taskSpecification, pageable)

        return taskPage.map { task -> taskResponseConverter.apply(task) }
    }

    override fun getTaskPage(pageable: Pageable, principal: Principal): Page<TaskResponse> {
        val user  = userRepository.findByEmail(principal.name)

        return taskRepository.findAllByUserId(user?.id!!, pageable)
            .map { taskResponseConverter.apply(it) }
    }

    override fun completeTask(id: String) {
        val task = taskRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Task not found with this id") }

        if (task.isCompleted == true) throw TaskAlreadyCompletedException("Task already completed with this id: '$id'")

        task.isCompleted = true
        taskRepository.save(task)
    }

}