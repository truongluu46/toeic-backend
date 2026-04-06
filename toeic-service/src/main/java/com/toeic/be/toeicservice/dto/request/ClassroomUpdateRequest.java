package com.toeic.be.toeicservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomUpdateRequest {

    private String className;
    private String classCode;
    private String description;

    private Set<String> studentId;
}
