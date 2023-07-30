package com.dev.taskservice.security

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationFilter(
    val jwtProvider: JWTProvider,
    @Qualifier("userdetailsservice") val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")

        if (authorizationHeader != null) {
            if (authorizationHeader.startsWith("Bearer ")) {
                val jwtToken = authorizationHeader.substring(7)
                if (jwtToken.isBlank()) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization Header")
                }
                val email = jwtProvider.extractEmail(jwtToken)
                val userDetails = userDetailsService.loadUserByUsername(email)

                if (jwtProvider.isNotExpired(jwtToken)) {
                    val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    SecurityContextHolder.getContext().authentication = authToken
                }
            }
        }

        filterChain.doFilter(request, response)
    }
}