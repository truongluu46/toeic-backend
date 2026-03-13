package com.toeic.be.toeicservice.repository;

import com.toeic.be.toeicservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Tự động tạo câu lệnh: SELECT * FROM users WHERE username = ?
    Optional<User> findByUsername(String username);

    // Tự động tạo câu lệnh kiểm tra email tồn tại chưa
    Boolean existsByEmail(String email);
}