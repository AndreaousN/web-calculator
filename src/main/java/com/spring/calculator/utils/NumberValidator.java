package com.spring.calculator.utils;

import com.spring.calculator.model.Number;
import com.spring.calculator.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class NumberValidator implements Validator {
    @Autowired
    @Qualifier("NumberService")
    private NumberService numberService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Number.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Number number = (Number) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number1", "NotEmpty");
        if (number.getNumber1() < 0) {
            errors.rejectValue("number1", "Number.negative.number");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number2", "NotEmpty");
        if (number.getNumber2() < 0) {
            errors.rejectValue("number2", "Number.negative.number");
        }
        if ((number.getNumber2() == 0) && (number.getOperation().equals("/"))) {
            errors.rejectValue("number2", "Number.divisionByZero.number");
        }
    }
}
