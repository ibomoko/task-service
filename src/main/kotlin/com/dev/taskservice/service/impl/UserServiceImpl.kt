package com.dev.taskservice.service.impl

import com.dev.taskservice.converter.user.UserCreateResponseConverter
import com.dev.taskservice.converter.user.UserEntityConverter
import com.dev.taskservice.entity.User
import com.dev.taskservice.error.exception.ResourceAlreadyExistsException
import com.dev.taskservice.model.request.user.UserCreateRequest
import com.dev.taskservice.model.response.user.UserCreateResponse
import com.dev.taskservice.repository.UserRepository
import com.dev.taskservice.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val userRepository: UserRepository,
                      val userEntityConverter: UserEntityConverter,
                      val userCreateResponseConverter: UserCreateResponseConverter) : UserService {
    override fun createUser(userCreateRequest: UserCreateRequest): UserCreateResponse {
        userRepository.findByEmail(userCreateRequest.email)
            ?.run { throw ResourceAlreadyExistsException("User already exists with this email: '$email") }

        var user: User = userEntityConverter.apply(userCreateRequest)
        user = userRepository.save(user)

        return userCreateResponseConverter.apply(user)
    }




}