package com.dev.taskservice.entity

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "roles")
class Role(@Id
           @GeneratedValue(generator = "id-generator")
           @GenericGenerator(
               name = "id-generator",
               strategy = "com.dev.taskservice.entity.generator.IdGenerator"
           ) val id: String?,
           @Column(name = "name") var name: String?) {

}