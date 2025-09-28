package com.krishnaallu009.springBoot.service;

import com.krishnaallu009.springBoot.dto.StudentDto;
import com.krishnaallu009.springBoot.dto.StudentResponseDto;
import com.krishnaallu009.springBoot.entity.Student;
import com.krishnaallu009.springBoot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapperService studentMapperService;

    public StudentService(StudentRepository studentRepository, StudentMapperService studentMapperService) {
        this.studentRepository = studentRepository;
        this.studentMapperService = studentMapperService;
    }

    public StudentResponseDto saveStudent(StudentDto studentDto) {
        var student = studentMapperService.toStudent(studentDto);
        var savedStudent = studentRepository.save(student);

        return studentMapperService.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream().map(studentMapperService::toStudentResponseDto)
                .toList();
    }

    public StudentResponseDto findStudentById(Integer id) {
        return studentRepository.findById(id)
                .map(studentMapperService::toStudentResponseDto)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public List<StudentResponseDto> getStudentsByName(String name) {
        return studentRepository.findAllByFirstNameContaining(name).stream()
                .map(studentMapperService::toStudentResponseDto)
                .toList();
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }
}
