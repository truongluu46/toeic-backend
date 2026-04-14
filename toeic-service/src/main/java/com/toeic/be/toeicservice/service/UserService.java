package com.toeic.be.toeicservice.service;

import com.toeic.be.toeicservice.constant.Role;
import com.toeic.be.toeicservice.dto.request.UserCreationRequest;
import com.toeic.be.toeicservice.dto.request.UserUpdateRequest;
import com.toeic.be.toeicservice.dto.response.UserResponse;
import com.toeic.be.toeicservice.entity.User;
import com.toeic.be.toeicservice.exception.AppException;
import com.toeic.be.toeicservice.exception.ErrorCode;
import com.toeic.be.toeicservice.mapper.UserMapper;
import com.toeic.be.toeicservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;


    public UserResponse createUser(UserCreationRequest request){
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        if (userRepository.existsByEmail(request.getEmail())){
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));


//        user.setUsername(request.getUsername());
//
//        String encodedPassword = passwordEncoder.encode(request.getPassword());
//        user.setPassword(encodedPassword);
//        user.setEmail(request.getEmail());
//        user.setFullName(request.getFullName());
//        user.setRoles(Set.of(request.getRoles()));

         User savedUser = userRepository.save(user);
         return userMapper.toUserResponse(savedUser);
    }

    public List<User> getUsers(){

        return userRepository.findAll();
    }

    public UserResponse getUser(String id){
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);


         User savedUser = userRepository.save(user);
         return userMapper.toUserResponse(savedUser);
    }

    public void deleteUser(String userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userRepository.delete(user);
    }


}
