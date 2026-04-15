package com.toeic.be.toeicservice.repository;

import com.toeic.be.toeicservice.entity.TestSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestSessionRepository extends JpaRepository<TestSession, Long> {
}
