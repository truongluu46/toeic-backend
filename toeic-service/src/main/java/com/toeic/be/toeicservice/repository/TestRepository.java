package com.toeic.be.toeicservice.repository;

import com.toeic.be.toeicservice.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
