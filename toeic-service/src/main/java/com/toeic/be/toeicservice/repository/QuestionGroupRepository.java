package com.toeic.be.toeicservice.repository;

import com.toeic.be.toeicservice.entity.QuestionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionGroupRepository extends JpaRepository<QuestionGroup, Long> {
}
