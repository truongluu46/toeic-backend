package com.toeic.be.toeicservice.service;

import com.toeic.be.toeicservice.dto.request.QuestionRequest;
import com.toeic.be.toeicservice.entity.Test;
import com.toeic.be.toeicservice.entity.TestSession;
import com.toeic.be.toeicservice.entity.User;
import com.toeic.be.toeicservice.exception.AppException;
import com.toeic.be.toeicservice.exception.ErrorCode;
import com.toeic.be.toeicservice.repository.QuestionRepository;
import com.toeic.be.toeicservice.repository.ResultRepository;
import com.toeic.be.toeicservice.repository.TestRepository;
import com.toeic.be.toeicservice.repository.TestSessionRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TestSessionService {
    TestSessionRepository testSessionRepository;
    TestRepository testRepository;
    ResultRepository resultRepository;
    QuestionRepository questionRepository;

    public TestSession startTest(Long testId, User user){
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new AppException(ErrorCode.TEST_NOT_FOUND));

        // to do: check xem de thi hoan thien moi cho thi
        /* if (!test.isComplete() || !test.isPublic()) {
            throw new AppException(ErrorCode.TEST_NOT_AVAILABLE);
        }
        * */

        TestSession session = TestSession.builder()
                .user(user)
                .test(test)
                .startTime(LocalDateTime.now())
                .submitted(false)
                .build();
        return testSessionRepository.save(session);
    }







}
