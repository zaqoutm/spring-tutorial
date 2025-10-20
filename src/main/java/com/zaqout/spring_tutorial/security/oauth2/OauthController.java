package com.zaqout.spring_tutorial.security.oauth2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {

    @GetMapping("/profile/auth")
    public Object x(@AuthenticationPrincipal OAuth2User oAuth2User) {
        return oAuth2User;
    }
}
