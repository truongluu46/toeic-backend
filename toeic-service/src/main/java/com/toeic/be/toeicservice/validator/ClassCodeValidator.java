package com.toeic.be.toeicservice.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class ClassCodeValidator implements ConstraintValidator<ClassCodeConstraint, String> {
    private int min;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(s))
            return true;
        log.info("Checking string: {} with min: {}", s, min);
        return s.length() >= min;
    }

    @Override
    public void initialize(ClassCodeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }
}
