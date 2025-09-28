package com.krishnaallu009.springBoot.controller;

import com.krishnaallu009.springBoot.dto.SchoolDto;
import com.krishnaallu009.springBoot.dto.SchoolResponseDto;
import com.krishnaallu009.springBoot.entity.School;
import com.krishnaallu009.springBoot.repository.SchoolRepository;
import com.krishnaallu009.springBoot.service.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public SchoolResponseDto createSchool(@RequestBody SchoolDto schoolDto){
       return schoolService.createSchool(schoolDto);
    }

    @GetMapping("/schools")
    public List<SchoolResponseDto> getAllSchools() {
        return schoolService.getAllSchools();
    }
}
