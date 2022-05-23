package com.moazmahmud.spring_boot_thymeleaf.health_data.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CarbIntakeFrequency {
    ONE_TWO_TIMES("1-2 Times"),
    TWO_THREE_TIMES("2-3 Times"),
    THREE_FOUR_TIMES("3-4 Times"),
    MORE_THAN_FOUR_TIMES("More than 4 Times"),
    ;

    public static final List<CarbIntakeFrequency> carbIntakeFrequencyList = Stream.of(values()).collect(Collectors.toUnmodifiableList());

    private final String text;

    CarbIntakeFrequency(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
