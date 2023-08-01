package com.dev.taskservice.repository

import com.dev.taskservice.entity.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface UserRoleRepository : JpaRepository<UserRole, String> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_roles WHERE user_id = ?1", nativeQuery = true)
    fun deleteByUserId(id: String)

}