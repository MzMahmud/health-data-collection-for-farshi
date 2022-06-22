package com.moazmahmud.health_data_collection.common.enums;

import com.moazmahmud.health_data_collection.common.interfaces.TextGettable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Religion implements TextGettable {
    ISLAM("Islam"),
    HINDUISM("Hinduism"),
    CHRISTIANITY("Christianity"),
    BUDDHISM("Buddhism"),
    OTHER("Other"),
    PREFER_NOT_TO_TELL("Prefer Not to Tell"),
    ;
    public static final List<Religion> religionList = Stream.of(values()).collect(Collectors.toUnmodifiableList());

    private final String text;

    Religion(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}
