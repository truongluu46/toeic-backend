package com.toeic.be.toeicservice.constant;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN(1),
    TEACHER(2),
    STUDENT(3);

    private final int value;

    Role(int value) {
        this.value = value;
    }
}
