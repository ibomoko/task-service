package com.dev.taskservice.model.request.user

import javax.validation.constraints.NotEmpty

data class UserSignInRequest(
    @field:NotEmpty(message = "Email is required") val email: String,
    @field:NotEmpty(message = "Password is required") val password: String
)
