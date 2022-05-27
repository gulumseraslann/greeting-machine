package com.example.greetingmachine.service;

import org.springframework.util.ObjectUtils;

public class GreetingService {

    public String greet(String name) {
        ObjectUtils.isEmpty(name);
    }
}
