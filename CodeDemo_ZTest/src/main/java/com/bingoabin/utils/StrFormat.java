package com.bingoabin.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Supplier;

public class StrFormat {

    private static final int MAX_MSG = 512;

    private final LazyInit<String> pattern;

    private StrFormat(Supplier<String>... pattern) {
        if (pattern == null || pattern.length == 0) {
            throw new IllegalArgumentException("should pass one or more args");
        }
        this.pattern = LazyInit.of(() -> {
            StringBuilder result = new StringBuilder();
            for (Supplier<String> supplier : pattern) {
                String str = supplier.get();
                result.append(str == null ? "" : str);
            }
            return result.toString().replaceAll("\\{\\w*}", "%s");
        });
    }


    /**
     * Reformat str{i}ng
     * <p>
     * ```java
     * StrFormat.of("%s ","str{i}ng"," %s").format("hello","iii","format").equals("hello striiing format")
     * ```
     *
     * @param pattern
     */
    public static StrFormat of(Supplier<String>... pattern) {
        return new StrFormat(pattern);
    }

    public String format(Object... args) {
        if (args == null || args.length == 0) {
            return pattern.get();
        }
        return String.format(pattern.get(), args);
    }

    public static String trimMessage(Exception ex) {
        String message = ex.getMessage();
        return trimMessage(message, MAX_MSG);
    }

    public static String trimMessage(String message, int maxLength) {
        return StringUtils.isEmpty(message) ? null :
                message.length() > maxLength ? message.substring(0, maxLength) + "..." : message;
    }
}
