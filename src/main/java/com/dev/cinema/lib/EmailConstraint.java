package com.dev.cinema.lib;

import com.dev.cinema.model.validator.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailConstraint {
    String message() default "Email is invalid!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
