package com.krishnaallu009.springBoot.repository;


import com.krishnaallu009.springBoot.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
