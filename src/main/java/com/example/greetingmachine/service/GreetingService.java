package com.example.greetingmachine.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class GreetingService {

    public GreetingService() {
    }

    public String greet(String name) {
        if (ObjectUtils.isEmpty(name)) {
            return "Hello world.";
        }
        String[] split = name.split(",");
        List<String> uppercaseNames = new ArrayList<>();
        List<String> lowercaseNames = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {

            boolean allUpperCase = StringUtils.isAllUpperCase(split[i]);
            if (StringUtils.isAllUpperCase(split[i])) {
                uppercaseNames.add(split[i]);
            } else {
                lowercaseNames.add(split[i]);
            }
        }
        return splitTextViaCommaAndGetResult(lowercaseNames) + "AND" + splitTextViaCommaAndGetResult(uppercaseNames);

        String helloWord = "Hello, ";
        String helloWord1 = splitTextViaCommaAndGetResult(name, helloWord);
        if (helloWord1 != null) return helloWord1;
        return StringUtils.isAllUpperCase(name) ? "HELLO, " + name + '!' : "Hello, " + name + '.';
    }

    private String splitTextViaCommaAndGetResult(List<String> names) {
        int size = names.size();
        String helloWord = StringUtils.isAllUpperCase(names.get(0)) ? "HELLO" : "Hello";
        String andWord = StringUtils.isAllUpperCase(names.get(0)) ? "AND" : "and";
        for (int i = 0; i < size; i++) {

            //if (StringUtils.contains(name, ",")) {
            if (size > 2) {
                // for (int i = 0; i < size; i++) {


                helloWord = helloWord + names.get(i);
                if (i != size - 1) {
                    helloWord = i == size - 2 ? helloWord + ", " + andWord : helloWord + ", ";
                } else {
                    helloWord = helloWord + '.';
                }
            }
            return helloWord;

            String replacedName = StringUtils.replace(names.get(0), ",", " and");
            return "Hello, " + replacedName + '.';
        });

    }
}
