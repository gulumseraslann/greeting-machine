package com.example.greetingmachine.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class GreetingService {

    public String greet(String name) {
        /*if (ObjectUtils.isEmpty(name)) {

        }*/
        return name;
    }
}
