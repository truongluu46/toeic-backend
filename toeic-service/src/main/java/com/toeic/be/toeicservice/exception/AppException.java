package com.toeic.be.toeicservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {
    private ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        // define lại code để biết lỗi đẩy ra là mã mấy 400 500 ++
    }
}
