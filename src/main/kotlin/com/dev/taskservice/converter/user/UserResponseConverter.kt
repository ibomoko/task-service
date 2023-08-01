package com.dev.taskservice.converter.user

import com.dev.taskservice.entity.User
import com.dev.taskservice.model.response.user.UserResponse
import org.springframework.stereotype.Component
import java.util.function.Function

@Component
class UserResponseConverter: Function<User, UserResponse> {
    override fun apply(user: User): UserResponse {
        return UserResponse(user.id!!, user.fullname!!, user.email!!)
    }
}