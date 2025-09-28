package com.krishnaallu009.springBoot.service;

import com.krishnaallu009.springBoot.dto.StudentDto;
import com.krishnaallu009.springBoot.entity.School;
import com.krishnaallu009.springBoot.entity.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperServiceTest {

    private StudentMapperService studentMapperService;

    @BeforeEach
    void setUp(){
        studentMapperService = new StudentMapperService();
    }

    @Test
    public void shouldMapStudentDtoToStudentEntity(){
        StudentDto studentDto = new StudentDto(
                "Krishna",
                "Allu",
                "krishna@gmail.com",
                1
        );

        Student student = studentMapperService.toStudent(studentDto);
        assertEquals(studentDto.firstName(), student.getFirstName());
        assertEquals(studentDto.lastName(), student.getLastName());
        assertEquals(studentDto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentDto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenStudentDtoIsNull(){
        var msg = assertThrows(NullPointerException.class, () ->
                studentMapperService.toStudent(null)
        );
        assertEquals("StudentDto should not be null", msg.getMessage());
    }

    @Test
    public void shouldMapStudentEntityToStudentResponseDto() {
        Student student = new Student();
        student.setFirstName("Bhargavi");
        student.setLastName("Ram");
        student.setEmail("rambha@gmail.com");
        student.setAge(20);

        var studentResponseDto = studentMapperService.toStudentResponseDto(student);
        assertEquals(student.getFirstName(), studentResponseDto.firstName());
        assertEquals(student.getLastName(), studentResponseDto.lastName());
        assertEquals(student.getEmail(), studentResponseDto.email());
    }
}