package com.toeic.be.toeicservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String audioUrl;

    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String passage;

    @ManyToOne
    @JoinColumn(name = "part_id")
    @JsonIgnore
    private Part part;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Question> questions;

}
