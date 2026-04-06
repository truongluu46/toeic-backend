package com.toeic.be.toeicservice.service;

import com.toeic.be.toeicservice.dto.request.ClassCreationRequest;
import com.toeic.be.toeicservice.entity.Classroom;
import com.toeic.be.toeicservice.entity.User;
import com.toeic.be.toeicservice.exception.AppException;
import com.toeic.be.toeicservice.exception.ErrorCode;
import com.toeic.be.toeicservice.repository.ClassroomRepository;
import com.toeic.be.toeicservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;
    private final UserRepository userRepository;
    ClassroomService(ClassroomRepository classroomRepository, UserRepository userRepository){
        this.classroomRepository = classroomRepository;
        this.userRepository = userRepository;
    }

    public Classroom createClassroom(ClassCreationRequest request, String userId){
        if (classroomRepository.findByClassName(request.getClassName()).isPresent()) {
            throw new AppException(ErrorCode.CLASSNAME_EXISTED);
        }

        if (classroomRepository.findByClassCode(request.getClassCode()).isPresent()) {
            throw new AppException(ErrorCode.CLASSCODE_EXISTED);
        }

        User owner = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Classroom classroom = new Classroom();
        classroom.setClassName(request.getClassName());
        classroom.setClassCode(request.getClassCode());
        classroom.setDescription(request.getDescription());
        classroom.setOwner(owner);

        return classroomRepository.save(classroom);
    }

    public Classroom getClassDetail(Long classId){
        return classroomRepository.findById(classId).orElseThrow(() -> new AppException(ErrorCode.CLASS_NOT_FOUND));
    }

    public List<Classroom> getAllClassroom(){
        return classroomRepository.findAll();
    }

}
