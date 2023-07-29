package com.dev.taskservice.service

import com.dev.taskservice.model.request.user.UserCreateRequest
import com.dev.taskservice.model.response.user.UserCreateResponse

interface UserService {
    fun createUser(userCreateRequest: UserCreateRequest): UserCreateResponse
}