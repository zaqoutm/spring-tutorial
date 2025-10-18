package com.zaqout.spring_tutorial;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestBcrypt {

    @Test
    void bCryptPassEncodingTest() {
        String password = "secret";
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encoded = delegatingPasswordEncoder.encode(password);

        boolean matches = delegatingPasswordEncoder.matches(password, encoded);
        assertThat(matches).isTrue();
        assertThat(encoded).startsWith("{bcrypt}");
    }
}
