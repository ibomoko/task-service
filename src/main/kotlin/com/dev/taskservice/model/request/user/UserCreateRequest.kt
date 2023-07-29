package com.dev.taskservice.model.request.user

import com.dev.taskservice.enum.RoleType
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size


data class UserCreateRequest(@NotEmpty(message = "Fullname is required")
                             @Size(max = 100, message = "Fullname cannot be longer than 100 characters")
                             val fullname: String,
    @NotEmpty(message = "Email is required")
    @Size(max = 100, message = "Email cannot be longer than 100 characters")
    val email: String,
    @NotEmpty(message = "Password is required")
    @Size(max = 100, message = "Password cannot be longer than 100 characters")
    val password: String,
    @NotEmpty(message = "At least one role is required") val roles: List<String>
) {
}