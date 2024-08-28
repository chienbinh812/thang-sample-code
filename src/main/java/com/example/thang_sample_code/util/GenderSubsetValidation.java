package com.example.thang_sample_code.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class GenderSubsetValidation implements ConstraintValidator<GenderSubset, Gender> {
    private Gender[] genders;
    @Override
    public boolean isValid(Gender value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || Arrays.asList(genders).contains(value);
    }

    @Override
    public void initialize(GenderSubset constraintAnnotation) {
        this.genders = constraintAnnotation.anyOf();
    }
}
