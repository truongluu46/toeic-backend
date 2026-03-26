package com.toeic.be.toeicservice.entity;


import jakarta.persistence.*;
import com.toeic.be.toeicservice.entity.Part;
import lombok.*;


import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Test {
    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private String title;
    private int duration;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Part> parts;


}
