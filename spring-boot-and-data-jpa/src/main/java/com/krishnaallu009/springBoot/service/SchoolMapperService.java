package com.krishnaallu009.springBoot.service;

import com.krishnaallu009.springBoot.dto.SchoolDto;
import com.krishnaallu009.springBoot.dto.SchoolResponseDto;
import com.krishnaallu009.springBoot.dto.StudentDto;
import com.krishnaallu009.springBoot.dto.StudentResponseDto;
import com.krishnaallu009.springBoot.entity.School;
import com.krishnaallu009.springBoot.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapperService {

    public School toSchool(SchoolDto schoolDto){
        School school = new School();
        school.setName(schoolDto.name());
        return school;
    }

    public SchoolResponseDto toSchoolResponseDto(School school){
        return new SchoolResponseDto(
                school.getName()
        );
    }
}
