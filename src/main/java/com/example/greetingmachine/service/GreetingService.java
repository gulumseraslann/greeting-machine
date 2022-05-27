package com.example.greetingmachine.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GreetingService {

    public String greet(String name) {
        if (ObjectUtils.isEmpty(name)) {
            return "Hello world.";
        }

        String[] names = name.split(",");

        if (names.length == 2) {
            return getGreetingForCouples(Arrays.asList(names));
        }

        if (names.length > 2) {
            return getGreetingForMultiplePeople(Arrays.asList(names));
        }

        return getGreetingForSinglePerson(name);
    }

    private String getGreetingForSinglePerson(String name) {
        String helloWord = StringUtils.isAllUpperCase(name) ? "HELLO" : "Hello";
        String punctuation = StringUtils.isAllUpperCase(name) ? "!" : ".";

        return String.format("%s, %s%s", helloWord, name, punctuation);
    }

    private String getGreetingForCouples(List<String> names) {
        String helloWord = StringUtils.isAllUpperCase(names.get(0)) ? "HELLO" : "Hello";
        String andWord = StringUtils.isAllUpperCase(names.get(0)) ? "AND" : "and";
        String punctuation = StringUtils.isAllUpperCase(names.get(0)) ? "!" : ".";

        return String.format("%s, %s %s %s%s", helloWord, names.get(0), andWord, names.get(1), punctuation);
    }

    private String getGreetingForMultiplePeople(List<String> names) {
        List<String> uppercaseNames = new ArrayList<>();
        List<String> lowercaseNames = new ArrayList<>();
        for (String name : names) {
            if (StringUtils.isAllUpperCase(name)) {
                uppercaseNames.add(name);
            } else {
                lowercaseNames.add(name);
            }
        }
        String greetingForLowercases = getGreetingForPeople(lowercaseNames);
        String greetingForUppercases = getGreetingForPeople(uppercaseNames);

        if (!StringUtils.isEmpty(greetingForLowercases) && !StringUtils.isEmpty(greetingForUppercases)) {
            return String.format("%s AND %s", greetingForLowercases, greetingForUppercases);
        }

        return !StringUtils.isEmpty(greetingForLowercases) ? greetingForLowercases : greetingForUppercases;

    }

    private String getGreetingForPeople(List<String> names) {
        if (names.size() == 0) {
            return "";
        }

        if (names.size() == 2) {
            return getGreetingForCouples(names);
        }

        int size = names.size();
        String helloWord = StringUtils.isAllUpperCase(names.get(0)) ? "HELLO" : "Hello";
        String andWord = StringUtils.isAllUpperCase(names.get(0)) ? "AND" : "and";
        String punctuation = StringUtils.isAllUpperCase(names.get(0)) ? "!" : ".";

        String greeting = helloWord + ", ";
        for (int i = 0; i < size; i++) {
            greeting += names.get(i);
            if (i != size - 1) {
                greeting = i == size - 2 ? greeting + ", " + andWord + " " : greeting + ", ";
            } else {
                greeting += punctuation;
            }
        }
        return greeting;
    }
}
