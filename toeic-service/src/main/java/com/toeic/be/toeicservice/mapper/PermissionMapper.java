package com.toeic.be.toeicservice.mapper;

import com.toeic.be.toeicservice.dto.request.PermissionRequest;
import com.toeic.be.toeicservice.dto.response.PermissionResponse;
import com.toeic.be.toeicservice.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
