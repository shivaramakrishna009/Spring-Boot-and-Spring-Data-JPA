package com.krishnaallu009.springBoot.service;

import com.krishnaallu009.springBoot.dto.StudentDto;
import com.krishnaallu009.springBoot.dto.StudentResponseDto;
import com.krishnaallu009.springBoot.entity.Student;
import com.krishnaallu009.springBoot.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentServiceTest {

    // Which service methods to test?
    @InjectMocks
    private StudentService studentService;

    //Declare the dependencies of the service
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapperService studentMapperService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSuccessfullySaveAStudent(){
        // Given
        StudentDto studentDto = new StudentDto(
                "Krishna",
                "Allu",
                "krishna@gmail.com",
                1
        );
        Student student = new Student(
                "Krishna",
                "Allu",
                "krishna@gmail.com",
                24
        );
        Student savedStudent = new Student(
                "Krishna",
                "Allu",
                "krishna@gmail.com",
                24
        );
        savedStudent.setId(1);

        // Mock the behavior of studentMapperService and studentRepository
        Mockito.when(studentMapperService.toStudent(studentDto)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn(savedStudent);
        Mockito.when(studentMapperService.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto(
                        savedStudent.getFirstName(),
                        savedStudent.getLastName(),
                        savedStudent.getEmail()
                ));

        // When
        StudentResponseDto studentResponseDto = studentService.saveStudent(studentDto);

        // Then
        assertEquals(studentDto.firstName(), studentResponseDto.firstName());
        assertEquals(studentDto.lastName(), studentResponseDto.lastName());
        assertEquals(studentDto.email(), studentResponseDto.email());

        // Verify that the mocked methods were called
        Mockito.verify(studentMapperService, Mockito.times(1))
                .toStudent(studentDto);
        Mockito.verify(studentRepository, Mockito.times(1))
                .save(student);
        Mockito.verify(studentMapperService, Mockito.times(1))
                .toStudentResponseDto(savedStudent);
    }

    @Test
    public void shouldReturnAllStudents() {
        // Given
        Student student1 = new Student(
                "Krishna",
                "Allu",
                "krishna@gmail.com",
                24
        );
        student1.setId(1);
        Student student2 = new Student(
                "Bhargavi",
                "J",
                "bhanu@gmail.com",
                22
        );
        student2.setId(2);

        // Mock the behavior of studentRepository
        Mockito.when(studentRepository.findAll()).thenReturn(List.of(student1, student2));
        Mockito.when(studentMapperService.toStudentResponseDto(student1))
                .thenReturn(new StudentResponseDto(
                        student1.getFirstName(),
                        student1.getLastName(),
                        student1.getEmail()
                ));
        Mockito.when(studentMapperService.toStudentResponseDto(student2))
                .thenReturn(new StudentResponseDto(
                        student2.getFirstName(),
                        student2.getLastName(),
                        student2.getEmail()
                ));
        // When
        var students = studentService.getAllStudents();

        // Then
        assertEquals(2, students.size());
        assertEquals("Krishna", students.get(0).firstName());
        assertEquals("Bhargavi", students.get(1).firstName());

        // Verify that the mocked methods were called
        Mockito.verify(studentRepository, Mockito.times(1))
                .findAll();
        Mockito.verify(studentMapperService, Mockito.times(1))
                .toStudentResponseDto(student1);
        Mockito.verify(studentMapperService, Mockito.times(1))
                .toStudentResponseDto(student2);
    }

    @Test
    public void shouldFindStudentById(){
        // Given
        Integer studentId = 1;
        Student student = new Student(
                "Bhargavi",
                "J",
                "rambha@gmail.com",
                22
        );
        student.setId(studentId);

        // Mock the behavior of studentRepository
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        Mockito.when(studentMapperService.toStudentResponseDto(student))
                .thenReturn(new StudentResponseDto(
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail()
                ));

        // When
        var studentResponseDto = studentService.findStudentById(studentId);

        // Then
        assertEquals("Bhargavi", studentResponseDto.firstName());
        assertEquals("J", studentResponseDto.lastName());
        assertEquals("rambha@gmail.com", studentResponseDto.email());

        // Verify that the mocked methods were called
        Mockito.verify(studentRepository, Mockito.times(1))
                .findById(studentId);
        Mockito.verify(studentMapperService, Mockito.times(1))
                .toStudentResponseDto(student);
    }

    @Test
    public void shouldReturnStudentsByName() {
        // Given
        String name = "Krishna";
        Student student1 = new Student(
                "Krishna",
                "Allu",
                "krishna@gmail.com",
                24
        );
        student1.setId(1);
        Student student2 = new Student(
                "Bhargavi",
                "J",
                "bhanu@gmail.com",
                20
        );
        student2.setId(2);
        Student student3 = new Student(
                "Krishna",
                "Kumar",
                "kumar@gmail.com",
                22
        );
        student3.setId(3);

        // Mock the behavior of studentRepository
        Mockito.when(studentRepository.findAllByFirstNameContaining(name))
                .thenReturn(List.of(student1, student3));
        Mockito.when(studentMapperService.toStudentResponseDto(student1))
                .thenReturn(new StudentResponseDto(
                        student1.getFirstName(),
                        student1.getLastName(),
                        student1.getEmail()
                ));
        Mockito.when(studentMapperService.toStudentResponseDto(student3))
                .thenReturn(new StudentResponseDto(
                        student3.getFirstName(),
                        student3.getLastName(),
                        student3.getEmail()
                ));

        // When
        var students = studentService.getStudentsByName(name);

        // Then
        assertEquals(2, students.size());
        assertEquals("Krishna", students.get(0).firstName());
        assertEquals("Krishna", students.get(1).firstName());
        assertEquals("Allu", students.get(0).lastName());
        assertEquals("Kumar", students.get(1).lastName());

        // Verify that the mocked methods were called
        Mockito.verify(studentRepository, Mockito.times(1))
                .findAllByFirstNameContaining(name);
        Mockito.verify(studentMapperService, Mockito.times(1))
                .toStudentResponseDto(student1);
        Mockito.verify(studentMapperService, Mockito.times(1))
                .toStudentResponseDto(student3);
    }

    @Test
    public void shouldDeleteStudentById(){
        // Given
        Integer studentId = 1;

        // When
        studentService.delete(studentId);

        // Then
        // Verify that the mocked method was called
        Mockito.verify(studentRepository, Mockito.times(1))
                .deleteById(studentId);
    }
}