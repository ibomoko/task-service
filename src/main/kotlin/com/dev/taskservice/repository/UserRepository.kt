package com.dev.taskservice.repository

import com.dev.taskservice.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, String> {
    fun findByEmail(email: String): User?
}