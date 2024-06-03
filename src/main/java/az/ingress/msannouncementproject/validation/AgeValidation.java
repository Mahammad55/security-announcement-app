package az.ingress.msannouncementproject.validation;

import az.ingress.msannouncementproject.annotation.ValidAge;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeValidation implements ConstraintValidator<ValidAge, Integer> {
    @Override
    public void initialize(ValidAge constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        if (age < 18) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("age must be at least 18").addConstraintViolation();
            return false;
        }

        if (age > 100) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("age must be at most 100").addConstraintViolation();
            return false;
        }

        return true;
    }
}
