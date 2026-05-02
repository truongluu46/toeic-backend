package com.toeic.be.toeicservice.dto.request;


import com.toeic.be.toeicservice.entity.User;
import com.toeic.be.toeicservice.validator.ClassCodeConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassCreationRequest {

    @NotBlank(message = "Tên lớp không được để trống")
    private String className;

   // @NotBlank(message = "Không được để trống classcode")
    @ClassCodeConstraint(min = 6, message = "CLASSCODE_INVALID")
    @NotNull
    private String classCode;

    private String description;

    private String ownerId;

    private Set<String> studentIds;





}
