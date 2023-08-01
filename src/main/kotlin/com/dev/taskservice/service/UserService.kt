package com.dev.taskservice.service

import com.dev.taskservice.model.request.user.UserSignUpRequest
import com.dev.taskservice.model.request.user.UserSignInRequest
import com.dev.taskservice.model.response.user.UserResponse
import com.dev.taskservice.model.response.user.UserSignUpResponse
import com.dev.taskservice.model.response.user.UserSignInResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserService {
    fun signUp(userSignUpRequest: UserSignUpRequest): UserSignUpResponse
    fun signIn(userSignInRequest: UserSignInRequest): UserSignInResponse
    fun deleteById(id: String)
    fun getUsers(pageable: Pageable): Page<UserResponse>
}