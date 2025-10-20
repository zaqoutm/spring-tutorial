package com.zaqout.spring_tutorial.security.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    public UsersService(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String loginAndGenerateJWT(AuthRequest request) {
        Authentication authenticationResponse = this.authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(request.getUsername(), request.getPassword()));
        if (authenticationResponse.isAuthenticated())
            return jwtService.generateToken((UserDetails) authenticationResponse.getPrincipal());
        return null;
    }
}
