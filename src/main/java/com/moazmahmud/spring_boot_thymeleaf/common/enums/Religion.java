package com.moazmahmud.spring_boot_thymeleaf.common.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Religion {
    ISLAM("Islam"),
    HINDUISM("Hinduism"),
    CHRISTIANITY("Cristianity"),
    BUDDHISM("Buddhism"),
    OTHER("Other"),
    PREFER_NOT_TO_TELL("Prefer Not to Tell"),
    ;
    public static final List<Religion> religionList = Stream.of(values()).collect(Collectors.toUnmodifiableList());

    private final String text;

    Religion(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
