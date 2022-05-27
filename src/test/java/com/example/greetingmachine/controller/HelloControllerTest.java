package com.example.greetingmachine.controller;

import com.example.greetingmachine.service.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class HelloControllerTest {
    @InjectMocks
    private HelloController sut;

    @Mock
    private GreetingService greetingService;

    private MockMvc mockMvc;


    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(sut)
                .build();
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void greet_should_greet_world_when_name_is_empty(String name) throws Exception {
        //
        when(greetingService.greet(anyString())).thenReturn("Hello, World");

        //when
        MvcResult mvcResult = this.mockMvc
                .perform(get("/hello"))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        //then
    }
}
