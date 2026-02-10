package com.yug.bfhl.util;

import com.yug.bfhl.exception.InvalidRequestException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RequestValidator {

    private static final Set<String> ALLOWED_KEYS = Set.of(
            "fibonacci", "prime", "lcm", "hcf", "AI");

    public static void validate(Map<String, Object> body) {
        if (body == null || body.isEmpty()) {
            throw new InvalidRequestException("Request body is missing or empty");
        }

        if (body.size() != 1) {
            throw new InvalidRequestException("Exactly one key must be present");
        }

        String key = body.keySet().iterator().next();

        if (!ALLOWED_KEYS.contains(key)) {
            throw new InvalidRequestException("Unknown key: " + key);
        }

        Object value = body.get(key);

        if (value == null) {
            throw new InvalidRequestException("Value for '" + key + "' must not be null");
        }

        if ("AI".equals(key)) {
            validateAiValue(value);
        } else {
            validateNumberArray(key, value);
        }
    }

    private static void validateAiValue(Object value) {
        if (!(value instanceof String)) {
            throw new InvalidRequestException("Value for 'AI' must be a string");
        }
        String str = (String) value;
        if (str.trim().isEmpty()) {
            throw new InvalidRequestException("Value for 'AI' must not be empty");
        }
    }

    private static void validateNumberArray(String key, Object value) {
        if (!(value instanceof List)) {
            throw new InvalidRequestException("Value for '" + key + "' must be an array");
        }

        List<?> list = (List<?>) value;

        if (list.isEmpty()) {
            throw new InvalidRequestException("Array for '" + key + "' must not be empty");
        }

        for (int i = 0; i < list.size(); i++) {
            Object item = list.get(i);
            if (item == null) {
                throw new InvalidRequestException("Element at index " + i + " is null");
            }
            if (!(item instanceof Number)) {
                throw new InvalidRequestException("Element at index " + i + " is not a number");
            }
            if (((Number) item).doubleValue() < 0) {
                throw new InvalidRequestException("Element at index " + i + " is negative");
            }
        }
    }

}
