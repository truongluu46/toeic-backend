package com.toeic.be.toeicservice.mapper;

import com.toeic.be.toeicservice.dto.request.PermissionRequest;
import com.toeic.be.toeicservice.dto.request.RoleRequest;
import com.toeic.be.toeicservice.dto.response.PermissionResponse;
import com.toeic.be.toeicservice.dto.response.RoleResponse;
import com.toeic.be.toeicservice.entity.Permission;
import com.toeic.be.toeicservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
