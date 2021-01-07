package com.springsecurity.sample.inmemory;

import com.springsecurity.sample.AuthenticationManager;
import com.springsecurity.sample.Constants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@ConditionalOnProperty(name = Constants.AUTH_MANAGER_PROPERTY, havingValue = "inMemory")
public class InMemorySecurity implements AuthenticationManager {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("muser")
                .password("muser")
                .roles("USER")
                .and()
                .withUser("madmin")
                .password("madmin")
                .roles("USER", "ADMIN")
                .and()
                .withUser("mnone")
                .password("mnone")
                .roles("NONE");
    }
}
