package com.dev.taskservice.security

import com.dev.taskservice.constants.SecurityConstants
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(val securityConstants: SecurityConstants,
                     val authenticationFilter: AuthenticationFilter,
                     @Qualifier("userdetailsservice") val userDetailsService: UserDetailsService,
                     val passwordEncoder: PasswordEncoder) {

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder)
        return authenticationProvider
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            csrf { disable() }

            authorizeRequests {
                securityConstants.whiteList.forEach { authorize(it, permitAll) }
                authorize(anyRequest, authenticated)
            }

            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS}
        }

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }


}