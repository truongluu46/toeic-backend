package com.toeic.be.toeicservice.controller;

import com.toeic.be.toeicservice.dto.response.ApiResponse;
import com.toeic.be.toeicservice.entity.Classroom;
import com.toeic.be.toeicservice.service.ClassroomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;
    public ClassroomController(ClassroomService classroomService){
        this.classroomService = classroomService;
    }

}
