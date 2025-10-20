package com.zaqout.spring_tutorial.security;

import com.zaqout.spring_tutorial.security.oauth2.CustomSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class ConfigurationOAuth2 {

    CustomSuccessHandler customSuccessHandler;

    public ConfigurationOAuth2(CustomSuccessHandler customSuccessHandler) {
        this.customSuccessHandler = customSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/", "/index.html", "/profile/auth").permitAll()
                                .anyRequest().authenticated()
                )

                .oauth2Login(oauth ->
                                oauth
                                        .successHandler(customSuccessHandler)
//                                .defaultSuccessUrl("/profile/oauth", true)
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                )
                .build();
    }
}
