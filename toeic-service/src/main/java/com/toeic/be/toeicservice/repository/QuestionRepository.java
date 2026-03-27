package com.toeic.be.toeicservice.repository;

import com.toeic.be.toeicservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
