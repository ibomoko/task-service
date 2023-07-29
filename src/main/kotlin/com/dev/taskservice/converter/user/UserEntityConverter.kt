package com.dev.taskservice.converter.user

import com.dev.taskservice.entity.Role
import com.dev.taskservice.entity.User
import com.dev.taskservice.enum.RoleType
import com.dev.taskservice.error.exception.InvalidArgumentException
import com.dev.taskservice.model.request.user.UserCreateRequest
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Function

@Component
class UserEntityConverter : Function<UserCreateRequest, User> {
    override fun apply(request: UserCreateRequest): User {
        validateRequestRoles(request.roles)

        return User(
            null,
            request.fullname,
            request.password,
            request.email,
            request.roles.map { roleName -> Role(null, roleName) }.toSet(),
            Date(),
            false
        )
    }

    private fun validateRequestRoles(roles: List<String>): Unit {
        val roleTypes: List<String> = RoleType.values().map { it.name }

        val invalidRoles = roles.filter { it !in roleTypes }
        if (invalidRoles.isNotEmpty()) throw InvalidArgumentException("Roles are invalid.")
    }
}