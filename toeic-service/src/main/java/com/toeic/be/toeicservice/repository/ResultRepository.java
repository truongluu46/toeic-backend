package com.toeic.be.toeicservice.repository;

import com.toeic.be.toeicservice.entity.Result;
import com.toeic.be.toeicservice.entity.Test;
import com.toeic.be.toeicservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
    boolean existsByUserAndTest(User user, Test test);
}
