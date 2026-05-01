package com.toeic.be.toeicservice.controller;

import com.toeic.be.toeicservice.dto.request.PermissionRequest;
import com.toeic.be.toeicservice.dto.request.RoleRequest;
import com.toeic.be.toeicservice.dto.response.ApiResponse;
import com.toeic.be.toeicservice.dto.response.PermissionResponse;
import com.toeic.be.toeicservice.dto.response.RoleResponse;
import com.toeic.be.toeicservice.service.PermissionService;
import com.toeic.be.toeicservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .results(roleService.createRole(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAllRole(){
        return ApiResponse.<List<RoleResponse>>builder()
                .results(roleService.getAllRole())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> deleteRole(@PathVariable String role){
        roleService.deleteRole(role);
        return ApiResponse.<Void>builder().build();
    }
}
