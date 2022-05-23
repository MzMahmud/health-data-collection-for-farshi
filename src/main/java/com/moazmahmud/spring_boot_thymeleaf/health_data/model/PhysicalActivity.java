package com.moazmahmud.spring_boot_thymeleaf.health_data.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PhysicalActivity {
    BED_REST("Bed Rest", true),
    SEDENTARY("Sedentary"),
    LIGHT_ACTIVITY("Light Activity"),
    MODERATE("Moderate"),
    HIGH("High"),
    ;

    public static final List<PhysicalActivity> physicalActivityList = Stream.of(values()).collect(Collectors.toUnmodifiableList());

    private final String text;
    private final boolean isDefault;

    PhysicalActivity(String text) {
        this(text, false);
    }

    PhysicalActivity(String text, boolean isDefault) {
        this.text = text;
        this.isDefault = isDefault;
    }

    public String getText() {
        return text;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public static String getText(PhysicalActivity physicalActivity) {
        return physicalActivity == null ? "NOT PROVIDED" : physicalActivity.text;
    }
}
