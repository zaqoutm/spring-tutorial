package com.zaqout.spring_tutorial.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final UserDetailsService userDetailsService;

    public MyController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/security/hello")
    String hello() {
        UserDetails mo = userDetailsService.loadUserByUsername("mo");
        return "Hello " + mo.getUsername();
    }
}
