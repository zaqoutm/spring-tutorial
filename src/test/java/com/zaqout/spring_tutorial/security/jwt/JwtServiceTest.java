package com.zaqout.spring_tutorial.security.jwt;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService("TEST+eyjHbGciOiJFUzI1NiIasdaqwrqrXZ");
    private final static Collection<GrantedAuthority> authorities = new ArrayList<>();

    @BeforeAll
    static void init() {
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        authorities.add(new SimpleGrantedAuthority("user"));
    }

    @Test
    void generateToken() {
        String token = jwtService.generateToken(new User("mo", "pass", authorities));
        String string = jwtService.extractUsername(token);
        assertThat(string).isEqualTo("mo");
    }

    @Test
    void getClaims() {
        String token = jwtService.generateToken(new User("mo", "pass", authorities));
        Claims claims = jwtService.getClaims(token);
        assertThat(claims).isNotNull();
    }
}