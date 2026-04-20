package com.toeic.be.toeicservice.controller;


import com.toeic.be.toeicservice.dto.request.TestCreationRequest;
import com.toeic.be.toeicservice.dto.response.ApiResponse;
import com.toeic.be.toeicservice.entity.Test;
import com.toeic.be.toeicservice.service.TestService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestController {

    TestService testService;

    @GetMapping()
    List<Test> getTests(){
        return testService.getTests();
    }

    @GetMapping("/{testId}")
    ApiResponse<Test> getTestDetail(@PathVariable("testId") Long testId){
        ApiResponse<Test> apiResponse = new ApiResponse<>();
        apiResponse.setResults(testService.getTestDetail(testId));
        return apiResponse;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Test createTest(@RequestBody TestCreationRequest request) {
        return testService.createTest(request);
    }

    @PatchMapping("/{testId}")
    ApiResponse<Test> updateTest(@RequestBody TestCreationRequest request, @PathVariable("testId") Long testId ){
        ApiResponse<Test> apiResponse = new ApiResponse<>();
        apiResponse.setResults(testService.updateTest(testId, request));
        return apiResponse;
    }


    @DeleteMapping("/{testId}")
    String deleteTest(@PathVariable Long testId){
         testService.deleteTest(testId);
         return "Test has been deleted";
    }
}
