package com.moazmahmud.health_data_collection.health_data.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CerealQuality {
    SIMPLE("Simple"),
    COMPLEX("Complex"),
    BOTH("Both"),
    ;

    public static final List<CerealQuality> cerealQualityList = Stream.of(values()).collect(Collectors.toUnmodifiableList());

    private final String text;

    CerealQuality(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
