package com.dev.taskservice.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class UserRoleKey(
    @Column(name = "user_id") val userId: String?,
    @Column(name = "role_id") val roleId: String?
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserRoleKey

        if (userId != other.userId) return false
        if (roleId != other.roleId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId?.hashCode() ?: 0
        result = 31 * result + (roleId?.hashCode() ?: 0)
        return result
    }

}