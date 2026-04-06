package com.toeic.be.toeicservice.controller;

import com.toeic.be.toeicservice.dto.request.ClassCreationRequest;
import com.toeic.be.toeicservice.dto.request.ClassroomUpdateRequest;
import com.toeic.be.toeicservice.dto.response.ApiResponse;
import com.toeic.be.toeicservice.entity.Classroom;
import com.toeic.be.toeicservice.entity.User;
import com.toeic.be.toeicservice.service.ClassroomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;
    public ClassroomController(ClassroomService classroomService){
        this.classroomService = classroomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Classroom> createClassroom(@RequestBody ClassCreationRequest request) {
        ApiResponse<Classroom> apiResponse = new ApiResponse<>();

        apiResponse.setResults(classroomService.createClassroom(request, request.getOwnerId()));
        return apiResponse;
    }

    @GetMapping("/{classId}")
    public ApiResponse<Classroom> getClassDetail(@PathVariable Long  classId) {
        ApiResponse<Classroom> apiResponse = new ApiResponse<>();
        apiResponse.setResults(classroomService.getClassDetail(classId));
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<Classroom>> getAllClassroom(){
        ApiResponse<List<Classroom>> apiResponse = new ApiResponse<>();
        apiResponse.setResults(classroomService.getAllClassroom());
        return apiResponse;
    }

    @PatchMapping("/{classId}")
    ApiResponse<Classroom> updateClassroom(@PathVariable Long classId, ClassroomUpdateRequest request){
        ApiResponse<Classroom> apiResponse  = new ApiResponse<>();
        apiResponse.setResults(classroomService.updateClassroom(classId, request));
        return apiResponse;
    }
}
