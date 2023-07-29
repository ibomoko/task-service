package com.dev.taskservice.model.response.user

import com.dev.taskservice.entity.Role
import com.dev.taskservice.enum.RoleType
import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class UserCreateResponse(val id: String,
                              val fullname: String,
                              val email: String,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss") val createDate: Date,
                              val roles: List<String>,
                              val isDeleted: Boolean) {

}