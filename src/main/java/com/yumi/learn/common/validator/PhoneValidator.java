package com.yumi.learn.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneNumber ,Object> {

    public static final String REGEX_PHONE="^(13\\d|14[579]|15[^4\\D]|17[^49\\D]|18\\d)\\d{8}$";

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String phone = String.valueOf(o);
        if(phone.length()!=11){
            return false;
        }
        //正则校验
        return phone.matches(REGEX_PHONE);
    }
}
