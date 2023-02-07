package com.ischade.strap.dto.response;

import com.ischade.strap.model.Role;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record UserDetailsDto(
        int id,
        String login,
        String firstName,
        String lastName,
        byte age,
        String email,
        List<Role> roles
) {
    public String rolesStr() {
        return roles.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}


