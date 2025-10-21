package com.zaqout.spring_tutorial.security.oauth2;

import com.zaqout.spring_tutorial.security.AppUser;
import com.zaqout.spring_tutorial.security.CustomUserDetails;
import com.zaqout.spring_tutorial.security.jwt.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final Logger logger = LoggerFactory.getLogger(CustomSuccessHandler.class);

    public CustomSuccessHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.debug("google success auth");

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String name = oAuth2User.getName();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String picture = (String) attributes.get("picture");
        String username = (String) attributes.get("given_name");

        AppUser appUser = new AppUser();
        appUser
                .setUsername(username)
                .setOauth("google")
                .setEmail(email)
                .setRoles("ROLE_USER")
                .setPictureUrl(picture);
        CustomUserDetails customUserDetails = new CustomUserDetails(appUser);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities()));

        String token = jwtService.generateToken(customUserDetails);
        response.getWriter().write(token);
    }

}