package com.dev.taskservice.model.response.task

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.Date

data class TaskResponse(val id: String?,
                        val title: String?,
                        val description: String?,
                        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh::mm::ss") val createDate: Date?,
                        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh::mm::ss") val dueDate: Date?,
                        val isCompleted: Boolean?,
                        val isDeleted: Boolean?)
