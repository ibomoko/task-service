package com.dev.taskservice.converter.user

import com.dev.taskservice.entity.User
import com.dev.taskservice.model.response.user.UserSignUpResponse
import org.springframework.stereotype.Component
import java.util.function.Function

@Component
class UserCreateResponseConverter : Function<User, UserSignUpResponse> {
    override fun apply(user: User): UserSignUpResponse {
        return UserSignUpResponse(
            user.id!!,
            user.fullname!!,
            user.email!!,
            user.createDate!!,
            user.roles?.map { role -> role.name!! }!!.toList(),
            user.isDeleted!!
        )
    }
}