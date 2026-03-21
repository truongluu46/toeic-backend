package com.toeic.be.toeicservice.controller;

import com.toeic.be.toeicservice.dto.request.AuthenticationRequest;
import com.toeic.be.toeicservice.dto.response.ApiResponse;
import com.toeic.be.toeicservice.dto.response.AuthenticationResponse;
import com.toeic.be.toeicservice.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var results = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .results(results)
                .build();
    }



    }
