package com.krishnaallu009.springBoot.dto;

import jakarta.validation.constraints.NotNull;

public record StudentDto(
        @NotNull(message = "First name is required")
        String firstName,
        @NotNull(message = "Last name is required")
        String lastName,
        String email,
        Integer schoolId
) {
}
