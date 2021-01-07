package com.springsecurity.sample.userdetails;

import com.springsecurity.sample.AuthenticationManager;
import com.springsecurity.sample.Constants;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@ConditionalOnProperty(name = Constants.AUTH_MANAGER_PROPERTY, havingValue = "usersvc")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class UserDetailsServiceAuthentication implements AuthenticationManager {

    private final UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
