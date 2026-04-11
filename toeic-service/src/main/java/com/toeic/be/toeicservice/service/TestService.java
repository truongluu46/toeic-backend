package com.toeic.be.toeicservice.service;

import com.toeic.be.toeicservice.dto.request.GroupRequest;
import com.toeic.be.toeicservice.dto.request.PartRequest;
import com.toeic.be.toeicservice.dto.request.QuestionRequest;
import com.toeic.be.toeicservice.dto.request.TestCreationRequest;
import com.toeic.be.toeicservice.entity.Part;
import com.toeic.be.toeicservice.entity.Question;
import com.toeic.be.toeicservice.entity.QuestionGroup;
import com.toeic.be.toeicservice.entity.Test;
import com.toeic.be.toeicservice.exception.AppException;
import com.toeic.be.toeicservice.exception.ErrorCode;
import com.toeic.be.toeicservice.mapper.TestMapper;
import com.toeic.be.toeicservice.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestMapper testMapper;

    @Transactional
    public Test createTest(TestCreationRequest request) {

        Test test = testMapper.toTest(request);
//        test.setTitle(request.title);
//        test.setDuration(request.duration);
//        test.setDescription(request.description);
//
//        Set<Part> parts = new HashSet<>();
//
//        for (PartRequest pr : request.parts) {
//            Part part = new Part();
//            part.setPartNumber(pr.partNumber);
//            part.setTest(test);
//
//            Set<QuestionGroup> groups = new HashSet<>();
//
//            for (GroupRequest gr : pr.groups) {
//                QuestionGroup group = new QuestionGroup();
//                group.setAudioUrl(gr.audioUrl);
//                group.setImageUrl(gr.imageUrl);
//                group.setPassage(gr.passage);
//                group.setPart(part);
//
//                Set<Question> questions = new HashSet<>();
//
//                for (QuestionRequest qr : gr.questions) {
//                    Question q = new Question();
//                    q.setContent(qr.content);
//                    q.setOptionA(qr.optionA);
//                    q.setOptionB(qr.optionB);
//                    q.setOptionC(qr.optionC);
//                    q.setOptionD(qr.optionD);
//                    q.setCorrectAnswer(qr.correctAnswer);
//                    q.setGroup(group);
//
//                    questions.add(q);
//                }
//
//                group.setQuestions(questions);
//                groups.add(group);
//            }
//            part.setGroups(groups);
//            parts.add(part);
//        }
//        test.setParts(parts);
        return testRepository.save(test);
    }

    public List<Test> getTests() {
        return testRepository.findAll();
    }

    @Transactional
    public Test getTestDetail(Long id) {
//        Test test = testRepository.findByIdWithDetails(id).get();
//        System.out.println("Số lượng Part: " + test.getParts().size());
//        if (!test.getParts().isEmpty()) {
//            System.out.println("Số lượng Group của Part 1: " +
//                    test.getParts().iterator().next().getGroups().size());
//        }
//        return test;
        return testRepository.findByIdWithDetails(id).orElseThrow(() -> new AppException(ErrorCode.TEST_NOT_FOUND));
    }

    public void deleteTest(Long id) {
        Test test = testRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new AppException(ErrorCode.TEST_NOT_FOUND));
        testRepository.delete(test);
    }

    @Transactional
    public Test updateTest(Long id, TestCreationRequest request){
        Test test = getTestDetail(id);
        test.setTitle(request.title);
        test.setDuration(request.duration);
        test.setDescription(request.description);

        Set<Part> parts = new HashSet<>();

        for (PartRequest pr : request.parts) {
            Part part = new Part();
            part.setPartNumber(pr.partNumber);
            part.setTest(test);

            Set<QuestionGroup> groups = new HashSet<>();

            for (GroupRequest gr : pr.groups) {
                QuestionGroup group = new QuestionGroup();
                group.setAudioUrl(gr.audioUrl);
                group.setImageUrl(gr.imageUrl);
                group.setPassage(gr.passage);
                group.setPart(part);

                Set<Question> questions = new HashSet<>();

                for (QuestionRequest qr : gr.questions) {
                    Question q = new Question();
                    q.setContent(qr.content);
                    q.setOptionA(qr.optionA);
                    q.setOptionB(qr.optionB);
                    q.setOptionC(qr.optionC);
                    q.setOptionD(qr.optionD);
                    q.setCorrectAnswer(qr.correctAnswer);
                    q.setGroup(group);

                    questions.add(q);
                }

                group.setQuestions(questions);
                groups.add(group);
            }
            part.setGroups(groups);
            parts.add(part);
        }
        test.setParts(parts);
        return testRepository.save(test);

    }
}








