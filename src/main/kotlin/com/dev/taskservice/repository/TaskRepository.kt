package com.dev.taskservice.repository

import com.dev.taskservice.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository: JpaRepository<Task, String>, JpaSpecificationExecutor<Task> {
    fun findByTitle(title: String): Task?
}