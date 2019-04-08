package com.imokhonko.entities.validationRules;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Censorship validator implementation.
 * @see Сensorship
 */
public class CensorshipValidator implements ConstraintValidator<Сensorship, String> {

    private String[] forbiddenSubstrings;
    private String errorMessage;

    public void initialize(Сensorship constraintAnnotation) {
        forbiddenSubstrings = constraintAnnotation.forbiddenSubstrings();
        errorMessage = constraintAnnotation.message();
    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for(String forbiddenValue : forbiddenSubstrings) {
            if(s.contains(forbiddenValue)) {
                return false;
            }
        }
        return true;
    }

}
