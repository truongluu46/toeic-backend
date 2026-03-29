package com.toeic.be.toeicservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id @GeneratedValue
    private Long id;

    private String content;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    @JsonIgnore
    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private QuestionGroup group;
}
