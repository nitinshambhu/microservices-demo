package com.springsecurity.sample;

import com.springsecurity.sample.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class AuthManagerConfigureAdapter extends WebSecurityConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final JwtFilter jwtFilter;

    @Value("${app.props.centralized-auth}")
    private boolean isAuthCentralized;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.debug("AuthManagerConfigureAdapter :: Authentication : {} ", authenticationManager);
        authenticationManager.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("AuthManagerConfigureAdapter :: Authorization : {} ", authenticationManager);
        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        if(isAuthCentralized) {
            http.authorizeRequests()
                    .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/*").permitAll()
                    .anyRequest()
                    .authenticated();
        }

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public org.springframework.security.authentication.AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
