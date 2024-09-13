package com.recrutai.api.auth.user;

import com.recrutai.api.address.CityAddressRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequest {
    @NotEmpty
    @Size(max = 40)
    @Schema(example = "John", requiredMode = RequiredMode.REQUIRED)
    private final String firstName;

    @NotEmpty
    @Size(max = 40)
    @Schema(example = "Smith", requiredMode = RequiredMode.REQUIRED)
    private final String lastName;

    @Size(max = 150)
    @Schema(example = "Software Developer", requiredMode = RequiredMode.REQUIRED)
    private final String headline;

    @NotEmpty
    @Size(min = 6, max = 255)
    @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")
    @Schema(example = "john_smith@email.com", requiredMode = RequiredMode.REQUIRED)
    private final String email;

    @NotEmpty
    @Size(min = 12)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[~`!@#$%^&*()\\[\\]{}\\\\-_+=.,:;<>/|?]).{12,}$")
    @Schema(example = "Password#123", requiredMode = RequiredMode.REQUIRED)
    private final String password;

    @Valid
    @Schema(implementation = CityAddressRequest.class, requiredMode = RequiredMode.REQUIRED)
    private final CityAddressRequest location;

    public UserRequest(
            String firstName,
            String lastName,
            String headline,
            String email,
            String password,
            CityAddressRequest location
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.headline = headline;
        this.email = email;
        this.password = password;
        this.location = location;
    }

}
