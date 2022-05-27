package com.example.greetingmachine.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class GreetingService {

    public String greet(String name) {
        if (ObjectUtils.isEmpty(name)) {
            return "Hello, World";
        }
        if (StringUtils.contains(name,",")) {
            String replacedName = StringUtils.replace(name, ",", " and");
            return "Hello, " + replacedName;

        }
        return StringUtils.isAllUpperCase(name) ? "HELLO, " + name : "Hello, " + name;
    }
}
