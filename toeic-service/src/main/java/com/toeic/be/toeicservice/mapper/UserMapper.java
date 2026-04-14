package com.toeic.be.toeicservice.mapper;

import com.toeic.be.toeicservice.dto.request.UserCreationRequest;
import com.toeic.be.toeicservice.dto.request.UserUpdateRequest;
import com.toeic.be.toeicservice.dto.response.UserResponse;
import com.toeic.be.toeicservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse (User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
