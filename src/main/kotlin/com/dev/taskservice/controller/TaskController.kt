package com.dev.taskservice.controller

import com.dev.taskservice.model.request.task.TaskCreateRequest
import com.dev.taskservice.model.request.task.TaskFilter
import com.dev.taskservice.model.response.task.TaskCreateResponse
import com.dev.taskservice.model.response.task.TaskResponse
import com.dev.taskservice.service.TaskService
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping("v1/api/tasks")
class TaskController(val taskService: TaskService) {

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    fun createTask(@RequestBody @Valid taskCreateRequest: TaskCreateRequest, principal: Principal): ResponseEntity<TaskCreateResponse> {
        return ResponseEntity.ok(taskService.createTask(taskCreateRequest, principal))
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun getAllTasks(@ParameterObject taskFilter: TaskFilter, @ParameterObject pageable: Pageable): ResponseEntity<Page<TaskResponse>> {
        return ResponseEntity.ok(taskService.getTaskPage(taskFilter, pageable))
    }

    @PostMapping("/{id}/complete")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    fun completeTask(@PathVariable id: String): ResponseEntity<HttpStatus> {
        taskService.completeTask(id)
        return ResponseEntity.ok(HttpStatus.OK)
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    fun getUserTasks(@ParameterObject pageable: Pageable, authentication: Authentication, principal: Principal): ResponseEntity<Page<TaskResponse>> {
        return ResponseEntity.ok(taskService.getTaskPage(pageable, principal))
    }

}