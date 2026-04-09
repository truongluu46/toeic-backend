package com.toeic.be.toeicservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INVALID_JSON_FORMAT(6666,"The uploaded JSON format is invalid or has incorrect syntax", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001, "User already existed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002, "User not found", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(2001,"User name must have at least 3 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(2002, "Password must have at least 8 characters", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(2003, "Email is not valid", HttpStatus.BAD_REQUEST),
    FIELD_NOT_BLANK(2004, "This field cannot be blank", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1003, "Invalid message key", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1004, "Email already existed", HttpStatus.BAD_REQUEST),
    TEST_NOT_FOUND(1005, "Test not found", HttpStatus.NOT_FOUND),
    CLASSCODE_EXISTED(1006,"Classroom code already existed", HttpStatus.BAD_REQUEST),
    CLASSNAME_EXISTED(1007,"Classroom name already existed", HttpStatus.BAD_REQUEST),
    CLASS_NOT_FOUND(1008,"Classroom not found",HttpStatus.NOT_FOUND);
    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
