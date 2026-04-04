package com.toeic.be.toeicservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private String fullName;
    private boolean enabled;
    private Set<Integer> roles;
}
