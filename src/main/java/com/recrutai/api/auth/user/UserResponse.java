package com.recrutai.api.auth.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UserResponse {
    @Schema(example = "1")
    private final long id;

    @Schema(example = "John")
    private final String firstName;

    @Schema(example = "Smith")
    private final String lastName;

    @Schema(example = "Software Developer")
    private final String headline;

    @Schema(example = "john_smith@email.com")
    private final String email;

    @Schema(example = "Oxford, Oxfordshire")
    private final String location;

    public UserResponse(
            long id,
            String firstName,
            String lastName,
            String headline,
            String email,
            String location
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.headline = headline;
        this.email = email;
        this.location = location;
    }

}
