package com.krishnaallu009.springBoot.dto;

public record StudentDto(
        String firstName,
        String lastName,
        String email,
        Integer schoolId
) {
}
