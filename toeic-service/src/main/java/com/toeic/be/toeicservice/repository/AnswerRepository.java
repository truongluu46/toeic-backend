package com.toeic.be.toeicservice.repository;

import com.toeic.be.toeicservice.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
