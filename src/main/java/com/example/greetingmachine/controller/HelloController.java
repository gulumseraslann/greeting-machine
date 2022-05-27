package com.example.greetingmachine.controller;

import com.example.greetingmachine.service.GreetingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    //@Autowired
    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public String greet() {
        return  greetingService.greet(StringUtils.EMPTY);
    }

    @GetMapping("/{name}")
    public String greet(@PathVariable(name = "name", required = false) String name) {
        return  greetingService.greet(name);
    }

}
