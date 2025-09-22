package com.krishnaallu009.springBoot.repository;

import com.krishnaallu009.springBoot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
