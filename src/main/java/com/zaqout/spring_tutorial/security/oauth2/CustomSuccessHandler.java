package com.zaqout.spring_tutorial.security.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        System.out.println(authentication.getPrincipal());
//        System.out.println("Loading OAuth user!!");

        SecurityContext context = SecurityContextHolder.getContext();
        OAuth2User oAuth2User = (OAuth2User) context.getAuthentication().getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        System.out.println(oAuth2User.getName()); // store in user
        System.out.println(attributes.get("email"));
        System.out.println(attributes.get("picture"));
        System.out.println(attributes.get("email_verified"));
        System.out.println(attributes.get("given_name"));
        System.out.println(attributes.get("family_name"));
        System.out.println(attributes.get("name"));

        ////////// create user
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        var x = new UsernamePasswordAuthenticationToken(context.getAuthentication().getPrincipal(), null, authorities);
        context.setAuthentication(x);

        // do things here
        // register user, create jwt ..etc

        response.sendRedirect("/profile/auth");
    }
}