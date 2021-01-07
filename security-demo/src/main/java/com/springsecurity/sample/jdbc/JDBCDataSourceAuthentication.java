package com.springsecurity.sample.jdbc;

import com.springsecurity.sample.AuthenticationManager;
import com.springsecurity.sample.Constants;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(name = Constants.AUTH_MANAGER_PROPERTY, havingValue = "jdbc")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class JDBCDataSourceAuthentication implements AuthenticationManager {

    private final DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser(
                        User.withUsername("juser")
                                .password("{noop}juser")
                                .roles("USER")
                ).withUser(
                User.withUsername("jadmin")
                        .password("{noop}jadmin")
                        .roles("USER", "ADMIN")
        ).withUser(
                User.withUsername("none")
                        .password("{noop}none")
                        .roles("NONE")
        );

        /**
         *  We don't have to follow default schema.
         *  We can design out database of users any way we want.
         *
         *  1. Be it because our tables are structured different or we are using a remote database
         *  with a url to datasource - if we want to tell spring how to query username & authority
         *  from our db or datasource this is how to do it
         *
         *  2. The other way to do it passing UserDetailsService to auth object instead of datasource
         *  & let that service deal with how the info is fetched from db or data source as show in
         *  UserDetailsServiceAuthentication.java
         */
        /*auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled from users where username =?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username = ?");*/
    }
}
