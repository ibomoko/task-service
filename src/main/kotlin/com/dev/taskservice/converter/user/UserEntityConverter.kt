package com.dev.taskservice.converter.user

import com.dev.taskservice.entity.Role
import com.dev.taskservice.entity.User
import com.dev.taskservice.enum.RoleType
import com.dev.taskservice.error.exception.InvalidArgumentException
import com.dev.taskservice.model.request.user.UserSignUpRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.BiFunction
import java.util.function.Function

@Component
class UserEntityConverter(val passwordEncoder: PasswordEncoder) : BiFunction<UserSignUpRequest, Role, User> {
    override fun apply(request: UserSignUpRequest, role: Role): User {

        return User(
            null,
            request.fullname,
            passwordEncoder.encode(request.password),
            request.email,
            setOf(role),
            Date()
        )
    }

}