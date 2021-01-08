package com.springsecurity.sample.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final ArrayList<UserDetails> userDetails = new ArrayList();

    public MyUserDetailsService() {
        userDetails.add(new MyUserDetails("uuser", "uuser", "ROLE_USER"));
        userDetails.add(new MyUserDetails("uadmin", "uadmin", "ROLE_USER", "ROLE_ADMIN"));
        userDetails.add(new MyUserDetails("unone", "unone", "NONE"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetails.stream()
                .filter((user) -> username.equals(user.getUsername()))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " is not found"));
    }
}
