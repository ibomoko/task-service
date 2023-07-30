package com.dev.taskservice.model.request.task


data class TaskFilter(val titleSearchText: String?,
                      val createdStartDate: Long?,
                      val createdEndDate: Long?,
                      val dueStartDate: Long?,
                      val dueEndDate: Long?,
                      val isCompleted: Boolean?,
                      val isDeleted: Boolean?)
