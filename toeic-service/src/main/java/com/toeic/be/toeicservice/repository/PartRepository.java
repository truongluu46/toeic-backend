package com.toeic.be.toeicservice.repository;

import com.toeic.be.toeicservice.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
