package com.zaqout.spring_tutorial.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class SecuredControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void springSecurityWorks() throws Exception {
        mvc.perform(get("/hello")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void allowAdminAccess() throws Exception {
        mvc.perform(get("/hello")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "USER")
    void forbidUserAccessToAdminEndpoint() throws Exception {
        mvc.perform(get("/hello")).andExpect(status().isForbidden());
    }

}