package com.dev.taskservice.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.dev.taskservice.constants.SecurityConstants
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTProvider(private val securityConstants: SecurityConstants) {

    private val jwtVerifier: JWTVerifier = JWT
        .require(Algorithm.HMAC512(securityConstants.jwtSecret))
        .withSubject(securityConstants.jwtSubject)
        .withIssuer(securityConstants.jwtIssuer)
        .build()

    fun generateToken(email: String): String {
        return JWT.create()
            .withSubject(email)
            .withIssuedAt(Date())
            .withExpiresAt(getExpireDate())
            .withIssuer(securityConstants.jwtIssuer)
            .sign(Algorithm.HMAC256(securityConstants.jwtSecret))
    }

    fun extractEmail(token: String): String {
        val decodedJWT = jwtVerifier.verify(token)
        return decodedJWT.subject
    }

    fun isNotExpired(token: String): Boolean {
        val decodedJWT = jwtVerifier.verify(token)
        return decodedJWT.expiresAt.after(Date())
    }

    private fun getExpireDate(): Date {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.HOUR, 3)
        return currentDate.time
    }

}