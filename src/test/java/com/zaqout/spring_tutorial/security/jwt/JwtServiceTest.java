package com.zaqout.spring_tutorial.security.jwt;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @BeforeAll
    static void init() {
    }

    @Test
    void generateToken() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        authorities.add(new SimpleGrantedAuthority("user"));

        String token = jwtService.generateToken(new User("mo", "pass", authorities));
        System.out.println(token);

        String string = jwtService.extractUsername(token);
        System.out.println(string);
    }

    @Test
    void extractUsername() {
    }

    @Test
    void validateToken() {
    }
}