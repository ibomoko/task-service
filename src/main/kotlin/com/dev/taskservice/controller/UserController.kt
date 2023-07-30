package com.dev.taskservice.controller

import com.dev.taskservice.model.request.user.UserSignUpRequest
import com.dev.taskservice.model.request.user.UserSignInRequest
import com.dev.taskservice.model.response.user.UserSignUpResponse
import com.dev.taskservice.model.response.user.UserSignInResponse
import com.dev.taskservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("v1/api/users")
class UserController(val userService: UserService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid userSignUpRequest: UserSignUpRequest): ResponseEntity<UserSignUpResponse> {
        return ResponseEntity.ok(userService.signUp(userSignUpRequest))
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid userSignInRequest: UserSignInRequest): ResponseEntity<UserSignInResponse> {
        return ResponseEntity.ok(userService.signIn(userSignInRequest))
    }


}