package com.dev.taskservice.error

import ErrorResponse
import com.dev.taskservice.error.exception.InvalidArgumentException
import com.dev.taskservice.error.exception.ResourceAlreadyExistsException
import com.dev.taskservice.error.exception.ResourceNotFoundException
import com.dev.taskservice.error.exception.TaskAlreadyCompletedException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException::class)
    fun handleResourceAlreadyExistsException(exception: ResourceAlreadyExistsException): ResponseEntity<ErrorResponse> {
        return buildErrorResponse(exception.message, HttpStatus.CONFLICT)
    }
    @ExceptionHandler(InvalidArgumentException::class)
    fun handleInvalidArgumentException(exception: InvalidArgumentException): ResponseEntity<ErrorResponse> {
        return buildErrorResponse(exception.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(exception: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
        return buildErrorResponse(exception.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(exception: UsernameNotFoundException): ResponseEntity<ErrorResponse> {
        return buildErrorResponse(exception.message, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(TaskAlreadyCompletedException::class)
    fun handleTaskAlreadyCompleted(exception: TaskAlreadyCompletedException): ResponseEntity<ErrorResponse> {
        return buildErrorResponse(exception.message, HttpStatus.CONFLICT)
    }

    companion object {
        fun buildErrorResponse(message: String?, status: HttpStatus): ResponseEntity<ErrorResponse> {
            return ResponseEntity(ErrorResponse(message, status.value(), status), status)
        }
    }

}