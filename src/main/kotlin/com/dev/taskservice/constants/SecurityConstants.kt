package com.dev.taskservice.constants

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class SecurityConstants(@Value(value = "\${security.auth.whitelist}") val whiteList: Array<String>,
                        @Value(value = "\${security.jwt-secret}") val jwtSecret: String,
                        @Value(value = "\${security.jwt-issuer}") val jwtIssuer: String,
                        @Value(value = "\${security.jwt-subject}") val jwtSubject: String) {

}