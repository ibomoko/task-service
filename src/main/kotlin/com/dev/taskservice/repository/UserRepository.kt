package com.dev.taskservice.repository

import com.dev.taskservice.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface UserRepository: JpaRepository<User, String> {
    fun findByEmail(email: String): User?
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE id = ?1", nativeQuery = true)
    fun deleteUserById(id: String)
}