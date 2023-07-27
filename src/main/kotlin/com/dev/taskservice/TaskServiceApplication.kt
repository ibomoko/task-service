package com.dev.taskservice

import com.dev.taskservice.entity.generator.IdGenerator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskServiceApplication

fun main(args: Array<String>) {
    runApplication<TaskServiceApplication>(*args)
    var idGenerator = IdGenerator.generate()
}
