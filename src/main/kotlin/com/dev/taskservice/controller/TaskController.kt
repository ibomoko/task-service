package com.dev.taskservice.controller

import com.dev.taskservice.model.request.task.TaskCreateRequest
import com.dev.taskservice.model.response.task.TaskCreateResponse
import com.dev.taskservice.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("v1/api/tasks")
class TaskController(val taskService: TaskService) {

    @PostMapping
    fun createTask(@RequestBody @Valid taskCreateRequest: TaskCreateRequest): ResponseEntity<TaskCreateResponse> {
        return ResponseEntity.ok(taskService.createTask(taskCreateRequest))
    }


}