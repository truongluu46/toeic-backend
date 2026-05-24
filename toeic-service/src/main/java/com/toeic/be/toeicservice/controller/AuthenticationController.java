package com.toeic.be.toeicservice.controller;

import com.nimbusds.jose.JOSEException;
import com.toeic.be.toeicservice.dto.request.AuthenticationRequest;
import com.toeic.be.toeicservice.dto.request.IntrospectRequest;
import com.toeic.be.toeicservice.dto.request.LogoutRequest;
import com.toeic.be.toeicservice.dto.request.RefreshRequest;
import com.toeic.be.toeicservice.dto.response.ApiResponse;
import com.toeic.be.toeicservice.dto.response.AuthenticationResponse;
import com.toeic.be.toeicservice.dto.response.IntrospectResponse;
import com.toeic.be.toeicservice.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

     AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        AuthenticationResponse results = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .results(results)
                .build();
    }

    @PostMapping("/refreshtoken")
    public ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
        AuthenticationResponse results = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .results(results)
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request)
            throws ParseException,JOSEException{
         authenticationService.logout(request);
         return ApiResponse.<Void>builder().build();

    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody @Valid IntrospectRequest request)
    throws JOSEException, ParseException {
        IntrospectResponse results = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .results(results)
                .build();
    }
}
