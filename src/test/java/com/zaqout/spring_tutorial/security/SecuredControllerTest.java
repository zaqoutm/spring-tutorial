package com.zaqout.spring_tutorial.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@Import(SecurityConfig.class)
class SecuredControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void springSecurityWorks() throws Exception {
        mvc.perform(get("/")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void allowAdminAccess() throws Exception {
        mvc.perform(get("/security/hello")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "cat", password = "xx", roles = {"USER"})
    void forbidUserAccessToAdminEndpoint() throws Exception {
        mvc.perform(get("/security/hello")).andExpect(status().isForbidden());
    }
}