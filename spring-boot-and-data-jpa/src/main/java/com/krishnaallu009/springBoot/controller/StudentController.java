package com.krishnaallu009.springBoot.controller;

import com.krishnaallu009.springBoot.dto.StudentDto;
import com.krishnaallu009.springBoot.dto.StudentResponseDto;
import com.krishnaallu009.springBoot.entity.Student;
import com.krishnaallu009.springBoot.repository.StudentRepository;
import com.krishnaallu009.springBoot.service.StudentMapperService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentMapperService studentMapperService;

    public StudentController(StudentRepository studentRepository, StudentMapperService studentMapperService) {
        this.studentRepository = studentRepository;
        this.studentMapperService = studentMapperService;
    }

    @PostMapping("/students")
    public StudentResponseDto createStudent(@RequestBody StudentDto studentDto) {
        var student = studentMapperService.toStudent(studentDto);
        var savedStudent = studentRepository.save(student);

        return studentMapperService.toStudentResponseDto(savedStudent);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{student-id}")
    public Student getStudentById(@PathVariable("student-id") Integer id) {
        return studentRepository.findById(id).orElse(new Student());
    }

    @GetMapping("/students/search/{student-name}")
    public List<Student> getStudentByName(@PathVariable("student-name") String name) {
        return studentRepository.findAllByFirstNameContaining(name);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(@PathVariable("student-id") Integer id) {
        studentRepository.deleteById(id);
    }
}
