package com.toeic.be.toeicservice.service;

import com.toeic.be.toeicservice.dto.request.ClassCreationRequest;
import com.toeic.be.toeicservice.dto.request.ClassroomUpdateRequest;
import com.toeic.be.toeicservice.entity.Classroom;
import com.toeic.be.toeicservice.entity.User;
import com.toeic.be.toeicservice.exception.AppException;
import com.toeic.be.toeicservice.exception.ErrorCode;
import com.toeic.be.toeicservice.mapper.ClassroomMapper;
import com.toeic.be.toeicservice.repository.ClassroomRepository;
import com.toeic.be.toeicservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClassroomService {
     ClassroomRepository classroomRepository;
     UserRepository userRepository;
     ClassroomMapper classroomMapper;

    @Transactional
    public Classroom createClassroom(ClassCreationRequest request, String userId){
        if (classroomRepository.findByClassName(request.getClassName()).isPresent()) {
            throw new AppException(ErrorCode.CLASSNAME_EXISTED);
        }

        if (classroomRepository.findByClassCode(request.getClassCode()).isPresent()) {
            throw new AppException(ErrorCode.CLASSCODE_EXISTED);
        }

        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Classroom classroom = classroomMapper.toClassroom(request);
        classroom.setOwner(owner);

        Set<User> students = new HashSet<>();
        if (request.getStudentIds() != null) {
            for (String studentId : request.getStudentIds()) {
                User student = userRepository.findById(studentId)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
                students.add(student);
            }
        }
        classroom.setStudents(students);

        return classroomRepository.save(classroom);
    }

    public Classroom getClassDetail(Long classId){
        return classroomRepository.findById(classId).orElseThrow(() -> new AppException(ErrorCode.CLASS_NOT_FOUND));
    }

    public List<Classroom> getAllClassroom(){
        return classroomRepository.findAll();
    }

    @Transactional
    public Classroom updateClassroom(Long classId, ClassroomUpdateRequest request){
        Classroom classroom = getClassDetail(classId);

        if(!classroom.getClassCode().equals(request.getClassCode()) &&
        classroomRepository.findByClassCode(request.getClassCode()).isPresent()){
            throw new AppException(ErrorCode.CLASSCODE_EXISTED);
        }

        classroom.setClassName(request.getClassName());
        classroom.setDescription(request.getDescription());
        classroom.setClassCode(request.getClassCode());

        if (request.getStudentId() != null) {
            Set<User> students = new HashSet<>();
            for (String studentId : request.getStudentId()) {
                User student = userRepository.findById(studentId)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
                students.add(student);
            }
            classroom.setStudents(students);
        }


        return classroomRepository.save(classroom);
    }

}
