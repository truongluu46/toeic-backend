package com.toeic.be.toeicservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "users") // Đặt tên bảng là users (vì user là từ khóa nhạy cảm trong SQL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String fullName;

    private boolean enabled = true;

    // Phân quyền: Một người dùng có thể có nhiều vai trò (ROLE_USER, ROLE_ADMIN)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private Set<Integer> roles;
}