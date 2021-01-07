package com.springsecurity.sample;

import com.springsecurity.sample.jwt.AuthRequest;
import com.springsecurity.sample.jwt.JwtResponse;
import com.springsecurity.sample.jwt.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyController {

    private final TokenService tokenService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/")
    public String home() {
        final var home = "Home";
        return home;
    }

    @PostMapping("/authenticate")
    public JwtResponse generateToken(@RequestBody AuthRequest authRequest) {
        return tokenService.authenticate(authRequest);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String user() {
        return "All users";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Admins only";
    }
}
