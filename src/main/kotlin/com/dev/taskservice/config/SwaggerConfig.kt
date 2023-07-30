package com.dev.taskservice.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
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
            .components(Components().addSecuritySchemes("bearerAuth",
                        SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("jti")))
            .security(listOf(SecurityRequirement().addList("bearerAuth")))
            .info(Info().title("Task Service").version("V1"))
    }
}