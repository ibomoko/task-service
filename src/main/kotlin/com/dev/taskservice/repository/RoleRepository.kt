package com.dev.taskservice.repository

import com.dev.taskservice.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, String> {
    fun findByName(name: String): Role
}