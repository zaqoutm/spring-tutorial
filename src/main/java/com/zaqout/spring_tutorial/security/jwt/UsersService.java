package com.zaqout.spring_tutorial.security.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    UserDetailsService userDetailsService;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    public UsersService(UserDetailsService userDetailsService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String loginAndGenerateJWT(AuthRequest request) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(request.getUsername(), request.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        if (authenticationResponse.isAuthenticated()) {
            UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
            return jwtService.generateToken(user);
        }
        return null;
    }
}
