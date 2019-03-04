package com.imokhonko.entities.validationRules;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CensorshipValidatorTest {

    private CensorshipValidator censorshipValidator = null;

    private String[] forbiddenSubstrings = new String[]{"KEK", "LOL"};

    @Before
    public void setUp() {
        censorshipValidator = new CensorshipValidator();
        Сensorship constraintAnnotation = mock(Сensorship.class);

        when(constraintAnnotation.forbiddenSubstrings()).thenReturn(forbiddenSubstrings);
        when(constraintAnnotation.message()).thenReturn("");

        censorshipValidator.initialize(constraintAnnotation);
    }

    @Test
    public void isValid_HappyPath() {
        ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);
        assertTrue(censorshipValidator.isValid("HELLO DFKJ 342jk LF", constraintValidatorContext));
    }

    @Test
    public void isValid_BadPath() {
        ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);
        assertFalse(censorshipValidator.isValid("ASDfsKEK123 AKF", constraintValidatorContext));
    }

}