package com.toeic.be.toeicservice.controller;

import com.toeic.be.toeicservice.dto.response.ApiResponse;
import com.toeic.be.toeicservice.entity.TestSession;
import com.toeic.be.toeicservice.entity.User;
import com.toeic.be.toeicservice.service.TestSessionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestSessionController {
    TestSessionService testSessionService;

    @PostMapping("/{testId}/start")
    public ApiResponse<TestSession> startTest(@PathVariable Long testId, @AuthenticationPrincipal User user){
        TestSession session = testSessionService.startTest(Long testId,User user);

    }

}
