package com.spring.calculator.utils;

import com.spring.calculator.model.User;
import com.spring.calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    @Qualifier("UserService")
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User userFromDB = userService.getUserByUsername(user.getUsername());

        // Validacijos priemonių klasė (tuščių simbolių validavimui)
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "User.size.username");
        }
        // Check if the password is valid
        if (!isValidPassword(user.getPassword())) {
            errors.rejectValue("password", "User.invalid.password");
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "User.diff.passwordConfirm");
        }
        // Check if the username already exists
        if (userService.getUserByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "User.duplicate.username");
        }
        // Check if the email already exists
        if (userService.getUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "User.duplicate.email");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 21) {
            errors.rejectValue("password", "User.size.password");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 21) {
            errors.rejectValue("passwordConfirm", "User.size.passwordConfirm");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail() == null || user.getEmail().length() > 121) {
            errors.rejectValue("email", "User.size.email");
        }
        // Check if the user exists
        if (userFromDB != null && !bCryptPasswordEncoder.matches(user.getPassword(), userFromDB.getPassword())) {
            errors.rejectValue("password","User.exists.password");
        }
    }

    private boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,21}$");
    }
}
