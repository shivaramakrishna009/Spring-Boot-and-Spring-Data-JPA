package com.krishnaallu009.springBoot.controller;

import com.krishnaallu009.springBoot.dto.SchoolDto;
import com.krishnaallu009.springBoot.dto.SchoolResponseDto;
import com.krishnaallu009.springBoot.entity.School;
import com.krishnaallu009.springBoot.repository.SchoolRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {

    private SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    private School toSchool(SchoolDto schoolDto){
        School school = new School();
        school.setName(schoolDto.name());
        return school;
    }

    private SchoolResponseDto toSchoolResponseDto(School school){
        return new SchoolResponseDto(
                school.getName()
        );
    }

    @PostMapping("/schools")
    public SchoolResponseDto createSchool(@RequestBody SchoolDto schoolDto){
        var school = toSchool(schoolDto);
        var savedSchool = schoolRepository.save(school);

        return toSchoolResponseDto(savedSchool);
    }

    @GetMapping("/schools")
    public List<SchoolResponseDto> getAllSchools() {
        return schoolRepository.findAll()
                .stream()
                .map(this::toSchoolResponseDto)
                .toList();
    }
}
