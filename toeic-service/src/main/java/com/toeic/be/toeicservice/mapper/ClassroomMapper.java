package com.toeic.be.toeicservice.mapper;

import com.toeic.be.toeicservice.dto.request.ClassCreationRequest;
import com.toeic.be.toeicservice.entity.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "students", ignore = true)
    Classroom toClassroom(ClassCreationRequest request);
}
