package com.recrutai.api.organization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

// S = SMALL, M = MEDIUM, L = LARGE
public enum OrganizationSize {
    S1("1"),
    S2("2 - 10"),
    S3("11 - 50"),
    M1("51 - 200"),
    M2("201 - 500"),
    M3("501 - 1.000"),
    L1("1.001 - 5.000"),
    L2("5.001 - 10.000"),
    L3("10.001+");

    private final String value;

    OrganizationSize(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static OrganizationSize fromString(String value) {
        if (value == null) return null;
        return Arrays.stream(OrganizationSize.values())
                .filter(x -> x.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid value: " + value));
    }

}
