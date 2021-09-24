package org.codecraftlabs.cloudlift.data;

import java.util.Arrays;
import java.util.Optional;

public enum ContentType {
    APPLICATION_JSON("application/json"),
    TEXT_PLAIN("text/plain");

    private final String code;

    ContentType(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    public static Optional<ContentType> findByCode(String value) {
        return Arrays.stream(values()).filter(item -> item.code().equalsIgnoreCase(value)).findFirst();
    }
}
