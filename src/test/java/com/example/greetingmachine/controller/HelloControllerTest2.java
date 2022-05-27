package com.example.greetingmachine.controller;

import com.example.greetingmachine.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = HelloController.class)
@ExtendWith(SpringExtension.class)
public class HelloControllerTest2 {
    @Autowired
    MockMvc mockMvc;

    //@MockBean
    //private GreetingService greetingService;

    @Test
    void greetTheWorld() throws Exception {
        MvcResult result = mockMvc.perform(get("/hello"))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

        String contents = result.getResponse().getContentAsString();

        assertThat(contents).isEqualTo("Hello, World.");
    }

    @Test
    void greetAPerson() throws Exception {
        MvcResult result = mockMvc.perform(get("/hello/Fran"))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

        String contents = result.getResponse().getContentAsString();

        assertThat(contents).isEqualTo("Hello, Fran.");
    }
}