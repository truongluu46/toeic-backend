package com.toeic.be.toeicservice.dto.request;

import com.toeic.be.toeicservice.constant.Role;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {
    @NotBlank(message = "FIELD_NOT_BLANK")
    @Size(min = 3, message = "USERNAME_INVALID")
    private String username;

    @NotBlank(message = "FIELD_NOT_BLANK")
    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;

    @Email(message = "EMAIL_INVALID")
    private String email;

    private String fullName;

    @NotNull
    private Set<Integer> roles;
}
