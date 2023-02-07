package com.ischade.strap.dto.request;

import com.ischade.strap.model.Role;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.List;
@Builder
public record CreateUserRequestDto(
        @NotBlank @Size(min = 5, max = 10) String login,
        @NotBlank @Size(min = 3, max = 40) String firstName,
        @NotBlank @Size(min = 3, max = 40) String lastName,
        @NotBlank @Size(min = 6) String password,
        @Min(1) @Max(120) byte age,
        @NotBlank @Email String email,
        @NotEmpty List<Role> roles
) { }
