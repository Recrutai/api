package com.recrutaibackend.institution;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;

// S = SMALL, M = MEDIUM, L = LARGE
@Getter
public enum InstitutionSize {
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

    InstitutionSize(String value) {
        this.value = value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static InstitutionSize fromString(String value) {
        if (value == null) return null;
        return Arrays.stream(InstitutionSize.values())
                .filter(x -> x.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid value: " + value));
    }

}
