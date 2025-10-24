package com.zaqout.spring_tutorial.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExceptionsControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void shouldThrowIllegalArgumentException() throws Exception {
        mvc.perform(get("/exceptions/price").param("price", "100"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testValidation() throws Exception {
        String json = """
                    {
                        "email": ""
                    }
                """;
        mvc.perform(post("/exceptions/valid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testValidationOK200() throws Exception {
        String json = """
                    {
                        "email": "mo@email.com"
                    }
                """;

        mvc.perform(post("/exceptions/valid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("mo@email.comOK123"));
    }
}