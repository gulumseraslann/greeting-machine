package com.example.greetingmachine.service;

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
        String name = "Bora";

        //when
        String greet = greetingService.greet(name);

        //then
        assertThat(greet).isEqualTo("Hello, Bora");
    }
}