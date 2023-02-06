package com.ischade.strap.dto.request;

import com.ischade.strap.model.Role;
import jakarta.validation.constraints.*;

import java.util.List;

public record UpdateUserRequestDto(
        @Positive int id,
        @NotBlank @Size(min = 3, max = 40) String firstName,
        @NotBlank @Size(min = 3, max = 40) String lastName,
        @NotBlank @Size(min = 6) String password,
        @Min(1) @Max(120) byte age,
        @NotBlank @Email String email,
        @NotEmpty List<Role> roles
) { }
