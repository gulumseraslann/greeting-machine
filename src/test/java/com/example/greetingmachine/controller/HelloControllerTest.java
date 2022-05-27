package com.example.greetingmachine.controller;

import com.example.greetingmachine.service.GreetingService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {
    @Autowired
    private HelloController controller;

    private MockMvc mockMvc;


    @BeforeEach
    void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void greet_should_greet_world_when_name_is_empty(String name) throws Exception {
        //

        //when
        MvcResult mvcResult = this.mockMvc
                .perform(get("/hello"))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        //then
        assertThat(result).isEqualTo("Hello, world.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Annie"})
    void greet_should_greet_world_when_name_is_not_empty(String name) throws Exception {
        //given

        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/hello/{name}", name))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        //then
        assertThat(result).isEqualTo("Hello, " + name + '.');
    }

    @ParameterizedTest
    @ValueSource(strings = {"BART"})
    void greet_should_greet_world_when_name_is_shooting(String name) throws Exception {
        //given

        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/hello/{name}", name))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        //then
        assertThat(result).isEqualTo("HELLO, " + StringUtils.capitalize(name) + '!');
    }

    @ParameterizedTest
    @ValueSource(strings = {"Annie,Bart"})
    void greet_should_greet_world_when_names_for_couple(String name) throws Exception {
        //given

        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/hello/{name}", name))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        //then
        String replacedName = StringUtils.replace(name, ",", " and");
        assertThat(result).isEqualTo("Hello, " + replacedName + '.');
    }
}
