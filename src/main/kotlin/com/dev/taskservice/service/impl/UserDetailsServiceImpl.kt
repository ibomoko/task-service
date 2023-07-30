package com.dev.taskservice.service.impl

import com.dev.taskservice.entity.User
import com.dev.taskservice.security.CustomUserDetails
import com.dev.taskservice.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service(value = "userdetailsservice")
class UserDetailsServiceImpl(private val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username)
            ?: throw UsernameNotFoundException("User not found with this email: '${username}'")

        return CustomUserDetails(user.email, user.password, getAuthorities(user) )
    }


    private fun getAuthorities(user: User): MutableList<SimpleGrantedAuthority> {
        val userRoles = user.roles
        return userRoles!!.map { role -> SimpleGrantedAuthority(role.name)}.toMutableList()
    }
}