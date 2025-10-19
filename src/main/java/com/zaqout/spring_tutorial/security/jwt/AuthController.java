package com.zaqout.spring_tutorial.security.jwt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    UsersService usersService;

    public AuthController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequest request) {
        String token = usersService.loginAndGenerateJWT(request);

        if (token == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(token);
    }


}
