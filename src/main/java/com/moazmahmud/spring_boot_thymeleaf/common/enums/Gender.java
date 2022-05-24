package com.moazmahmud.spring_boot_thymeleaf.common.enums;

import com.moazmahmud.spring_boot_thymeleaf.common.interfaces.TextGettable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Gender implements TextGettable {
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

    @Override
    public String getText() {
        return text;
    }
}
