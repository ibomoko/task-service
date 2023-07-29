package com.dev.taskservice.model.response.task

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class TaskCreateResponse(val title: String,
                              val description: String,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") val dueDate: Date,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") val createDate: Date,
                              val isCompleted:Boolean,
                              val userId: String,
                              val isDeleted: Boolean)
