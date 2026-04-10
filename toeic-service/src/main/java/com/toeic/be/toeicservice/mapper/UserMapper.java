package com.toeic.be.toeicservice.mapper;

import com.toeic.be.toeicservice.dto.request.UserCreationRequest;
import com.toeic.be.toeicservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
}
