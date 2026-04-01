package com.toeic.be.toeicservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig     {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Cho phép POST tới /users
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        // Cho phép POST tới /auth/login
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        // Các request khác phải login
                        .requestMatchers(HttpMethod.GET, "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/{userId}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/users/{userId}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/users/{userId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/tests").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/tests").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/tests/{testId}").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/tests/{testId}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/tests/{testId}").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

}
