package com.dev.taskservice.entity

import org.hibernate.annotations.GenericGenerator
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tasks")
class Task(
    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(
        name = "id-generator",
        strategy = "com.dev.taskservice.entity.generator.IdGenerator"
    ) val id: String?,
    @Column(name = "title") var title: String?,
    @Column(name = "description") var description: String?,
    @Column(name = "due_date") var dueDate: Date?,
    @Column(name = "is_completed") var isCompleted: Boolean?,
    @Column(name = "create_date") var createDate: Date?,
    @Column(name = "update_date") var updateDate: Date?,
    @ManyToOne
    @JoinColumn(name = "user_id") var user: User?
) {


}