package com.dev.taskservice.controller

import com.dev.taskservice.model.request.user.UserSignUpRequest
import com.dev.taskservice.model.request.user.UserSignInRequest
import com.dev.taskservice.model.response.user.UserResponse
import com.dev.taskservice.model.response.user.UserSignUpResponse
import com.dev.taskservice.model.response.user.UserSignInResponse
import com.dev.taskservice.service.UserService
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun deleteUser(@PathVariable id: String): ResponseEntity<HttpStatus> {
        userService.deleteById(id)
        return ResponseEntity.ok(HttpStatus.OK)
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    fun getUsers(@ParameterObject pageable: Pageable): ResponseEntity<Page<UserResponse>> {
        return ResponseEntity.ok(userService.getUsers(pageable))
    }

}