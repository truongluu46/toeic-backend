package com.toeic.be.toeicservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="classroom")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor  @Builder
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ClassCode;

    private String ClassName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner ;

    @ManyToMany
    @JoinTable(
            name = "classroom_users",
            joinColumns = @JoinColumn(name= "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> students;

   @Builder.Default
    private LocalDateTime creatdAt = LocalDateTime.now();
}
