package com.dev.taskservice.controller

import com.dev.taskservice.model.request.user.UserCreateRequest
import com.dev.taskservice.model.response.user.UserCreateResponse
import com.dev.taskservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("v1/api/users")
class UserController(val userService: UserService) {

    @PostMapping
    fun createUser(@RequestBody @Valid userCreateRequest: UserCreateRequest): ResponseEntity<UserCreateResponse> {
        return ResponseEntity.ok(userService.createUser(userCreateRequest))
    }


}