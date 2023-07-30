package com.dev.taskservice.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(username: String?,
                        password: String?,
                        authorities: MutableCollection<out GrantedAuthority>?) :
    User(username, password, authorities) {

}