package com.dev.taskservice.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Value("\${base-url}")
    lateinit var baseUrl: String

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .servers(listOf(Server().url(baseUrl)))
            .info(Info().title("Task Service").version("V1"))
    }
}