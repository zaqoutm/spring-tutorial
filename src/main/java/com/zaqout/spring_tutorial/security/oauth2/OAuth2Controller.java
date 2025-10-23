package com.zaqout.spring_tutorial.security.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class OAuth2Controller {

    @Value("${google.clientId}")
    private String clientId;

    @Value("${google.clientSecret}")
    private String clientSecret;

    @Value("${google.callbackUrl}")
    private String callbackUrl;

    private static final String SCOPE = "openid profile email";

    private OAuth20Service service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    void init() {
        service = new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .defaultScope(SCOPE)
                .callback(callbackUrl)
                .build(GoogleApi20.instance());
    }

    @GetMapping("/login/google")
    String login() {
        return service.getAuthorizationUrl();
    }

    @GetMapping(value = "/login/oauth2/code/google", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<?> oauthCallback(@RequestParam(name = "code", required = false) String code,
                                    @RequestParam(name = "error", required = false) String error)
            throws IOException, ExecutionException, InterruptedException {

        // token
        OAuth2AccessToken token = service.getAccessToken(code);

        // request info
        String userInfoEndpoint = "https://openidconnect.googleapis.com/v1/userinfo";
        OAuthRequest request = new OAuthRequest(Verb.GET, userInfoEndpoint);
        service.signRequest(token, request);
        Response response = service.execute(request);

        // parse JSON body to Map
        Map userInfo = objectMapper.readValue(response.getBody(), Map.class);

        return ResponseEntity.ok(userInfo);
    }
}
