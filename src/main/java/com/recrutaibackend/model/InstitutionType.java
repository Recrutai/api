package com.recrutaibackend.model;

import lombok.Getter;

@Getter
public enum InstitutionType {
    PUBLIC("Pública"),
    PRIVATE("Privada"),
    ASSOCIATION("Associação"),
    SELF_EMPLOYED("Autônomo");

    private final String name;

    InstitutionType(String name) {
        this.name = name;
    }

}
