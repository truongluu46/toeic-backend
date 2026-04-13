package com.toeic.be.toeicservice.mapper;

import com.toeic.be.toeicservice.dto.request.TestCreationRequest;
import com.toeic.be.toeicservice.entity.Test;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TestMapper {
    Test toTest(TestCreationRequest request);

    @AfterMapping
    default void linkRelationships(@MappingTarget Test test) {
        System.out.println("@AfterMapping chạy, parts: " + test.getParts()); // debug

        if (test.getParts() == null) return;

        test.getParts().forEach(part -> {
            part.setTest(test);

            if (part.getGroups() == null) return;

            part.getGroups().forEach(group -> {
                group.setPart(part); //

                if (group.getQuestions() == null) return;

                group.getQuestions().forEach(question -> {
                    question.setGroup(group); //
                });
            });
        });
    }
}
