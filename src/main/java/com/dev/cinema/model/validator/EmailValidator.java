package com.dev.cinema.model.validator;

import com.dev.cinema.lib.EmailConstraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext
            constraintValidatorContext) {
        return emailField != null && emailField.matches("^[^@\\s]+@[^@\\s\\.]+\\.[^@\\.\\s]+$");
    }
}
