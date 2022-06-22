package com.moazmahmud.health_data_collection.health_data.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PhysicalActivity {
    BED_REST("Bed Rest"),
    SEDENTARY("Sedentary"),
    LIGHT_ACTIVITY("Light Activity"),
    MODERATE("Moderate"),
    HIGH("High"),
    ;

    public static final List<PhysicalActivity> physicalActivityList = Stream.of(values()).collect(Collectors.toUnmodifiableList());

    private final String text;

    PhysicalActivity(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
