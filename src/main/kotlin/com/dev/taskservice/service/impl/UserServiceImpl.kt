package com.dev.taskservice.service.impl

import com.dev.taskservice.converter.user.UserCreateResponseConverter
import com.dev.taskservice.converter.user.UserEntityConverter
import com.dev.taskservice.converter.user.UserResponseConverter
import com.dev.taskservice.entity.User
import com.dev.taskservice.error.exception.ResourceAlreadyExistsException
import com.dev.taskservice.error.exception.ResourceNotFoundException
import com.dev.taskservice.model.request.user.UserSignInRequest
import com.dev.taskservice.model.request.user.UserSignUpRequest
import com.dev.taskservice.model.response.user.UserResponse
import com.dev.taskservice.model.response.user.UserSignInResponse
import com.dev.taskservice.model.response.user.UserSignUpResponse
import com.dev.taskservice.repository.RoleRepository
import com.dev.taskservice.repository.UserRepository
import com.dev.taskservice.repository.UserRoleRepository
import com.dev.taskservice.security.JWTProvider
import com.dev.taskservice.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val userEntityConverter: UserEntityConverter,
    val userCreateResponseConverter: UserCreateResponseConverter,
    val authenticationManager: AuthenticationManager,
    val jwtProvider: JWTProvider,
    val userRoleRepository: UserRoleRepository,
    val userResponseConverter: UserResponseConverter
) : UserService {

    override fun signUp(userSignUpRequest: UserSignUpRequest): UserSignUpResponse {
        userRepository.findByEmail(userSignUpRequest.email)
            ?.run { throw ResourceAlreadyExistsException("User already exists with this email: '$email'") }

        val role = roleRepository.findByName("USER")
        var user: User = userEntityConverter.apply(userSignUpRequest, role)
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

    override fun deleteById(id: String) {
        val user = userRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("User not found with this id: '$id'") }

        userRepository.deleteUserById(user.id!!)
        userRoleRepository.deleteByUserId(user.id)
    }

    override fun getUsers(pageable: Pageable): Page<UserResponse> {
        return userRepository.findAll(pageable).map { userResponseConverter.apply(it) }
    }


}