package com.zaqout.spring_tutorial.security;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecuredController {

    //    @PreAuthorize("hasRole('ADMIN')")
//    @Secured("ROLE" + Role.ADMIN) // prefix needed
    @RolesAllowed(Role.ADMIN) // ROLE_ no need prefix
    @GetMapping("/hello")
    String hello(Principal principal) {
        return "Hello Admin " + principal.getName();
    }

    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping("/profile")
    Object profile(Principal principal) {
        return principal;
    }

}
