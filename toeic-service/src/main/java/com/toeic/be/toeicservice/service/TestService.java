package com.toeic.be.toeicservice.service;

import com.toeic.be.toeicservice.dto.request.GroupRequest;
import com.toeic.be.toeicservice.dto.request.PartRequest;
import com.toeic.be.toeicservice.dto.request.QuestionRequest;
import com.toeic.be.toeicservice.dto.request.TestCreationRequest;
import com.toeic.be.toeicservice.entity.Part;
import com.toeic.be.toeicservice.entity.Question;
import com.toeic.be.toeicservice.entity.QuestionGroup;
import com.toeic.be.toeicservice.entity.Test;
import com.toeic.be.toeicservice.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public Test createTest(TestCreationRequest request) {
        Test test = new Test();
        test.setTitle(request.title);
        test.setDuration(request.duration);
        test.setDescription(request.description);

        List<Part> parts = new ArrayList<>();

        for (PartRequest pr : request.parts) {
            Part part = new Part();
            part.setPartNumber(pr.partNumber);
            part.setTest(test);

            List<QuestionGroup> groups = new ArrayList<>();

            for (GroupRequest gr : pr.groups) {
                QuestionGroup group = new QuestionGroup();
                group.setAudioUrl(gr.audioUrl);
                group.setImageUrl(gr.imageUrl);
                group.setPassage(gr.passage);

                List<Question> questions = new ArrayList<>();

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

    public List<Test> getTests() {
        return testRepository.findAll();
    }
}








