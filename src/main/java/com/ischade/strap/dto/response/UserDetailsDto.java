package com.ischade.strap.dto.response;

import com.ischade.strap.model.Role;
import jakarta.validation.constraints.*;

import java.util.List;

public record UserDetailsDto(
        @Positive int id,
        @NotBlank String login,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Min(1) @Max(120) byte age,
        @NotBlank @Email String email,
        @NotEmpty List<Role> roles
) { }


