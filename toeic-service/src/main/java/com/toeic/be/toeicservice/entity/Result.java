package com.toeic.be.toeicservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id @GeneratedValue
    private String id;

    private int score;
    private int correctAnswers;

    @ManyToOne
    private User user;

    @ManyToOne
    private Test test;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL)
    private List<Answer> answers;
}
