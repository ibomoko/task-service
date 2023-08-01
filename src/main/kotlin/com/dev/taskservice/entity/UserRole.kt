package com.dev.taskservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
class UserRole(
    @EmbeddedId
    val id: UserRoleKey?,
    @ManyToOne
    @JoinColumn(name = "role_id")
    @MapsId(value = "roleId")
    val role: Role?,
    @Column(name = "role_id", updatable = false, insertable = false)
    val roleId: String?,
    @ManyToOne
    @MapsId(value = "userId")
    @JoinColumn(name = "user_id")
    val user: User?,
    @Column(name = "user_id", updatable = false, insertable = false)
    val userId: String?
) {

}
