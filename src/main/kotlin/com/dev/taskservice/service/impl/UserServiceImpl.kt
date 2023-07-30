package com.dev.taskservice.service.impl

import com.dev.taskservice.converter.user.UserCreateResponseConverter
import com.dev.taskservice.converter.user.UserEntityConverter
import com.dev.taskservice.entity.User
import com.dev.taskservice.error.exception.ResourceAlreadyExistsException
import com.dev.taskservice.model.request.user.UserSignInRequest
import com.dev.taskservice.model.request.user.UserSignUpRequest
import com.dev.taskservice.model.response.user.UserSignInResponse
import com.dev.taskservice.model.response.user.UserSignUpResponse
import com.dev.taskservice.repository.UserRepository
import com.dev.taskservice.security.JWTProvider
import com.dev.taskservice.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val userEntityConverter: UserEntityConverter,
    val userCreateResponseConverter: UserCreateResponseConverter,
    val authenticationManager: AuthenticationManager,
    val jwtProvider: JWTProvider
) : UserService {

    override fun signUp(userSignUpRequest: UserSignUpRequest): UserSignUpResponse {
        userRepository.findByEmail(userSignUpRequest.email)
            ?.run { throw ResourceAlreadyExistsException("User already exists with this email: '$email'") }

        var user: User = userEntityConverter.apply(userSignUpRequest)
        user = userRepository.save(user)

        return userCreateResponseConverter.apply(user)
    }

    override fun signIn(userSignInRequest: UserSignInRequest): UserSignInResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                userSignInRequest.email, userSignInRequest.password
            )
        )
        val user = userRepository.findByEmail(userSignInRequest.email)
        return UserSignInResponse(jwtProvider.generateToken(user!!.email!!))
    }


}