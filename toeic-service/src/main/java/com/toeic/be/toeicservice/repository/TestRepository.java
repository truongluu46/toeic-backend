package com.toeic.be.toeicservice.repository;

import com.toeic.be.toeicservice.entity.Test;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {
    @Query("""
    SELECT t FROM Test t
    LEFT JOIN FETCH t.parts p
    LEFT JOIN FETCH p.groups g
    LEFT JOIN FETCH g.questions
    WHERE t.id = :id
""")

    Optional<Test> findByIdWithDetails(@Param("id") Long id);
}
