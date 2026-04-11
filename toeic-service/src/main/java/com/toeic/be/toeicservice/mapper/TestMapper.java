package com.toeic.be.toeicservice.mapper;

import com.toeic.be.toeicservice.dto.request.TestCreationRequest;
import com.toeic.be.toeicservice.entity.Test;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestMapper {
    Test toTest(TestCreationRequest request);
}
