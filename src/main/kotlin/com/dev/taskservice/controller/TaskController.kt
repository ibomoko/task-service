package com.dev.taskservice.controller

import com.dev.taskservice.model.request.task.TaskCreateRequest
import com.dev.taskservice.model.request.task.TaskFilter
import com.dev.taskservice.model.response.task.TaskCreateResponse
import com.dev.taskservice.model.response.task.TaskResponse
import com.dev.taskservice.service.TaskService
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("v1/api/tasks")
class TaskController(val taskService: TaskService) {

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    fun createTask(@RequestBody @Valid taskCreateRequest: TaskCreateRequest): ResponseEntity<TaskCreateResponse> {
        return ResponseEntity.ok(taskService.createTask(taskCreateRequest))
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    fun getTaskPage(@ParameterObject taskFilter: TaskFilter, @ParameterObject pageable: Pageable): ResponseEntity<Page<TaskResponse>> {
        return ResponseEntity.ok(taskService.getTaskPage(taskFilter, pageable))
    }

}