package com.dev.taskservice.model.request.task

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.Date
import javax.validation.constraints.NotEmpty

data class TaskCreateRequest(@NotEmpty(message = "Title is required") val title: String,
                             @NotEmpty(message = "Description is required") val description: String,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") val dueDate: Date?,
                             @NotEmpty(message = "User email is required") val userEmail: String)
