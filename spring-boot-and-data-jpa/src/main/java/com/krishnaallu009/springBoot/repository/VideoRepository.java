package com.krishnaallu009.springBoot.repository;

import com.krishnaallu009.springBoot.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer> {

}
