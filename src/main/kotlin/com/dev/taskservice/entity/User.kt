package com.dev.taskservice.entity

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(
        name = "id-generator",
        strategy = "com.dev.taskservice.entity.generator.IdGenerator"
    ) val id: String?,
    @Column(name = "fullname") var fullname: String?,
    @Column(name = "password") var password: String?,
    @Column(name = "email") var email: String?,
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: Set<Role>?,
    @Column(name = "create_date") var createDate: Date?
) {
}