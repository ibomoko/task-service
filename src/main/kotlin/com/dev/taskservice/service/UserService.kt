package com.dev.taskservice.service

import com.dev.taskservice.model.request.user.UserSignUpRequest
import com.dev.taskservice.model.request.user.UserSignInRequest
import com.dev.taskservice.model.response.user.UserSignUpResponse
import com.dev.taskservice.model.response.user.UserSignInResponse

interface UserService {
    fun signUp(userSignUpRequest: UserSignUpRequest): UserSignUpResponse
    fun signIn(userSignInRequest: UserSignInRequest): UserSignInResponse
}