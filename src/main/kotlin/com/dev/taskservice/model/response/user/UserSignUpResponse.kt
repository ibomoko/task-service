package com.dev.taskservice.model.response.user

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class UserSignUpResponse(
    val id: String,
    val fullname: String,
    val email: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss") val createDate: Date,
    val roles: List<String>,
    val isDeleted: Boolean
)