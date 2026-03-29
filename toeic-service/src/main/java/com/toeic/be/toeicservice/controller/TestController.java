package com.toeic.be.toeicservice.controller;


import com.toeic.be.toeicservice.dto.request.TestCreationRequest;
import com.toeic.be.toeicservice.entity.Test;
import com.toeic.be.toeicservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping
    public Test createTest(@RequestBody TestCreationRequest request) {
        return testService.createTest(request);
    }
}
