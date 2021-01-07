package com.springsecurity.sample;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

public interface AuthenticationManager {
    void configure(AuthenticationManagerBuilder auth) throws Exception;
}
