package com.krishnaallu009.springBoot.repository;

import com.krishnaallu009.springBoot.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
