package com.moazmahmud.spring_boot_thymeleaf.common.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other"),
    PREFER_NOT_TO_TELL("Prefer Not To Tell"),
    ;

    public static final List<Gender> genderList = Stream.of(values()).collect(Collectors.toUnmodifiableList());

    private final String text;

    Gender(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static String getText(Gender gender) {
        return gender == null ? "NOT AVAILABLE" : gender.text;
    }
}
