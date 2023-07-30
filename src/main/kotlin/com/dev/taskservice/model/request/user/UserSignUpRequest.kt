package com.dev.taskservice.model.request.user

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size


data class UserSignUpRequest(
    @field:NotEmpty(message = "Fullname is required")
    @field:Size(max = 100, message = "Fullname cannot be longer than 100 characters")
    val fullname: String,

    @field:NotEmpty(message = "Email is required")
    @field:Size(max = 100, message = "Email cannot be longer than 100 characters")
    val email: String,

    @field:NotEmpty(message = "Password is required")
    @field:Size(min = 8, max = 50, message = "Password cannot be less than 8 characters and longer than 50 characters")
    val password: String
)