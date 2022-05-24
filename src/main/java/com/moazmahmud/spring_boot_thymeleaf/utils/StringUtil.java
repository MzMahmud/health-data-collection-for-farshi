package com.moazmahmud.spring_boot_thymeleaf.utils;

import com.moazmahmud.spring_boot_thymeleaf.common.interfaces.TextGettable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class StringUtil {
    private StringUtil() {
    }

    private static final String DEFAULT_DATE_FORMAT = "dd MMMM, yyyy";

    public static String getFormattedDate(LocalDate localDate) {
        if (localDate == null) {
            return "NOT PROVIDED";
        }
        return localDate.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }

    public static String getText(TextGettable textGettable) {
        return textGettable == null ? "<<NOT TEXT FOUND>>" : textGettable.getText();
    }
}
