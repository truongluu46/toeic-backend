package com.toeic.be.toeicservice.config;

import com.toeic.be.toeicservice.constant.Role;
import com.toeic.be.toeicservice.entity.User;
import com.toeic.be.toeicservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import tools.jackson.databind.json.JsonMapper;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppplicationInitConfig {

     PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, JsonMapper.Builder builder){
        return  args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                var roles = new HashSet<Integer>();
                roles.add(Role.ADMIN.getValue());
                User user = User.builder()
                        .username("admin")
                        .email("admin@system.local")
                        .fullName("System Admin")
                        .password(passwordEncoder.encode("admin123"))
                        .roles(roles)
                        .build();

                userRepository.save(user);
                log.warn("admin user has been created with default password: admin123, please change it!");
            }
        };
    }
}
