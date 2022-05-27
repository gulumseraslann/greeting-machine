package com.example.greetingmachine.service;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GreetingServiceTest {

    @InjectMocks
    private GreetingService greetingService;

    @Test
    void it_should_greet_person_with_name() {
        //given
        String name = "Annie";

        //when
        String greet = greetingService.greet(name);

        //then
        assertThat(greet).isEqualTo("Hello, Annie.");
    }

    @Test
    void it_should_greet_person_without_name() {
        //given

        //when
        String greet = greetingService.greet(StringUtils.EMPTY);

        //then
        assertThat(greet).isEqualTo("Hello world.");
    }

    @Test
    void it_should_greet_couple() {
        //given
        String name = "Annie,Bart";

        //when
        String greet = greetingService.greet(name);

        //then
        assertThat(greet).isEqualTo("Hello, Annie and Bart.");
    }

    @Test
    void it_should_greet_name_with_shooting() {
        //given
        String name = "BART";

        //when
        String greet = greetingService.greet(name);

        //then
        assertThat(greet).isEqualTo("HELLO, BART!");
    }

    @Test
    void it_should_greet_multiple_peoples() {
        //given
        String name = "Annie,Bart,Charles,Monique";

        //when
        String greet = greetingService.greet(name);

        //then
        assertThat(greet).isEqualTo("Hello, Annie, Bart, Charles, and Monique.");
    }

    @Test
    void it_should_greet_shouting_and_not_shouting_people_with_couples() {
        //given
        String name = "Annie,BART,Charles,MONIQUE";

        //when
        String greet = greetingService.greet(name);

        //then
        assertThat(greet).isEqualTo("Hello, Annie and Charles. AND HELLO, BART AND MONIQUE!");
    }

    @Test
    void it_should_greet_shouting_and_not_shouting_peoples() {
        //given
        String name = "Annie,BART,Charles,MONIQUE,Bora,GULUMSER";

        //when
        String greet = greetingService.greet(name);

        //then
        assertThat(greet).isEqualTo("Hello, Annie, Charles, and Bora. AND HELLO, BART, MONIQUE, AND GULUMSER!");
    }
}