package com.example.greetingmachine.controller;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
        assertThat(result).isEqualTo("Hello world.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Annie"})
    void greet_should_greet_person(String name) throws Exception {
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
    void greet_should_greet_shouting_person(String name) throws Exception {
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
    void greet_should_greet_couples(String name) throws Exception {
        //given

        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/hello/{name}", name))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        //then
        String replacedName = StringUtils.replace(name, ",", " and ");
        assertThat(result).isEqualTo("Hello, " + replacedName + '.');
    }

    @ParameterizedTest
    @ValueSource(strings = {"ANNIE,BART"})
    void greet_should_greet_shouting_couples(String name) throws Exception {
        //given

        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/hello/{name}", name))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        //then
        String replacedName = StringUtils.replace(name, ",", " AND ");
        assertThat(result).isEqualTo("HELLO, " + replacedName + '!');
    }

    @Test
    void greet_should_greet_multiple_nonshouting_people() throws Exception {
        //given
        String name = "Annie,Bart,Monique";

        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/hello/{name}", name))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        //then
        assertThat(result).isEqualTo("Hello, Annie, Bart, and Monique.");
    }

    @Test
    void greet_should_greet_multiple_shouting_people() throws Exception {
        //given
        String name = "ANNIE,BART,MONIQUE";

        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/hello/{name}", name))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        //then
        assertThat(result).isEqualTo("HELLO, ANNIE, BART, AND MONIQUE!");
    }

    @Test
    void greet_should_greet_multiple_shouting_and_nonshouting_people() throws Exception {
        //given
        String name = "Annie,BART,Charles,MONIQUE";

        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/hello/{name}", name))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        //then
        assertThat(result).isEqualTo("Hello, Annie and Charles. AND HELLO, BART AND MONIQUE!");
    }

    @Test
    void greet_should_greet_multiple_shouting_and_nonshouting_people_2() throws Exception {
        //given
        String name = "Annie,BART,Charles,MONIQUE,Bora,GULUMSER";

        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/hello/{name}", name))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        //then
        assertThat(result).isEqualTo("Hello, Annie, Charles, and Bora. AND HELLO, BART, MONIQUE, AND GULUMSER!");
    }
}
