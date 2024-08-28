package com.example.thang_sample_code.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
//import jakarta.validation.constraints.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EnumPatternValidator implements ConstraintValidator<EnumPattern, Enum<?>> {
    private Pattern pattern;

    @Override
    public boolean isValid(Enum<?> anEnum, ConstraintValidatorContext constraintValidatorContext) {
        if (anEnum == null) {
            return true;
        }

        Matcher matcher = pattern.matcher(anEnum.name());
        return matcher.matches();
    }

    @Override
    public void initialize(EnumPattern enumPattern) {
        try {
            pattern = Pattern.compile(enumPattern.regex());
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Given regex is invalid",e);
        }
    }
}
