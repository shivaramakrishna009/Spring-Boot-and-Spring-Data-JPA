package com.krishnaallu009.springBoot.controller;

import com.krishnaallu009.springBoot.dto.StudentDto;
import com.krishnaallu009.springBoot.dto.StudentResponseDto;
import com.krishnaallu009.springBoot.entity.School;
import com.krishnaallu009.springBoot.entity.Student;
import com.krishnaallu009.springBoot.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private Student toStudent(StudentDto studentDto){
        Student student = new Student();
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setEmail(studentDto.email());

        var school = new School();
        school.setId(studentDto.schoolId());

        student.setSchool(school);

        return student;
    }

    private StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(@Valid @RequestBody StudentDto studentDto) {
        var student = toStudent(studentDto);
        var savedStudent = studentRepository.save(student);

        return toStudentResponseDto(savedStudent);
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ){
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError)error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
