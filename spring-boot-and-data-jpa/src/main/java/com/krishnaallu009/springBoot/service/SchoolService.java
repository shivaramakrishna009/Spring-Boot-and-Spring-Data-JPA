package com.krishnaallu009.springBoot.service;

import com.krishnaallu009.springBoot.dto.SchoolDto;
import com.krishnaallu009.springBoot.dto.SchoolResponseDto;
import com.krishnaallu009.springBoot.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    private final SchoolMapperService schoolMapperService;
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolMapperService schoolMapperService, SchoolRepository schoolRepository) {
        this.schoolMapperService = schoolMapperService;
        this.schoolRepository = schoolRepository;
    }

    public SchoolResponseDto createSchool(SchoolDto schoolDto) {
        var school = schoolMapperService.toSchool(schoolDto);
        var savedSchool = schoolRepository.save(school);

        return schoolMapperService.toSchoolResponseDto(savedSchool);
    }

    public List<SchoolResponseDto> getAllSchools() {
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapperService::toSchoolResponseDto)
                .toList();
    }
}
