package com.toeic.be.toeicservice.service;

import com.toeic.be.toeicservice.constant.Role;
import com.toeic.be.toeicservice.dto.request.UserCreationRequest;
import com.toeic.be.toeicservice.dto.request.UserUpdateRequest;
import com.toeic.be.toeicservice.dto.response.UserResponse;
import com.toeic.be.toeicservice.entity.User;
import com.toeic.be.toeicservice.exception.AppException;
import com.toeic.be.toeicservice.exception.ErrorCode;
import com.toeic.be.toeicservice.mapper.UserMapper;
import com.toeic.be.toeicservice.repository.RoleRepository;
import com.toeic.be.toeicservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
     UserRepository userRepository;
     RoleRepository roleRepository;

     PasswordEncoder passwordEncoder;

     UserMapper userMapper;


    public UserResponse createUser(UserCreationRequest request){
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        if (userRepository.existsByEmail(request.getEmail())){
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

         User savedUser = userRepository.save(user);
         return userMapper.toUserResponse(savedUser);
    }

   // @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('UPDATE_DATA')")
    public List<User> getUsers(){
        log.info("In method get user");
        return userRepository.findAll();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id){
        log.info("In method get user by id");
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

         User savedUser = userRepository.save(user);
         return userMapper.toUserResponse(savedUser);
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user  = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserResponse(user);
    }

    public void deleteUser(String userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userRepository.delete(user);
    }


}
