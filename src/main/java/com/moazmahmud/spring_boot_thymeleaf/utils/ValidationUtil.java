package com.moazmahmud.spring_boot_thymeleaf.utils;

import java.util.Objects;
import java.util.stream.Stream;

public final class ValidationUtil {
    private ValidationUtil() {

    }

    public static boolean isAnyNull(Object... objects) {
        return Stream.of(objects)
                     .sequential()
                     .anyMatch(Objects::isNull);
    }
}
