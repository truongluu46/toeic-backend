package com.toeic.be.toeicservice.controller;

import com.toeic.be.toeicservice.dto.request.AuthenticationRequest;
import com.toeic.be.toeicservice.dto.response.ApiResponse;
import com.toeic.be.toeicservice.dto.response.AuthenticationResponse;
import com.toeic.be.toeicservice.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        AuthenticationResponse results = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .results(results)
                .build();
    }
}
