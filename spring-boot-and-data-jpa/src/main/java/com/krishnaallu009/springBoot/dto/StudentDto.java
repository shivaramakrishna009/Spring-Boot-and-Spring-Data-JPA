package com.krishnaallu009.springBoot.dto;

import jakarta.validation.constraints.NotNull;

public record StudentDto(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        String email,
        Integer schoolId
) {
}
