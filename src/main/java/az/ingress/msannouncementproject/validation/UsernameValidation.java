package az.ingress.msannouncementproject.validation;

import az.ingress.msannouncementproject.annotation.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidation implements ConstraintValidator<ValidUsername, String> {
    @Override
    public void initialize(ValidUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (username.isBlank()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("username must not be empty").addConstraintViolation();
        }

        if (username.length() < 2 || username.length() >30) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("required username must be min 2, max 30 character").addConstraintViolation();
            return false;
        }

        return true;
    }
}
