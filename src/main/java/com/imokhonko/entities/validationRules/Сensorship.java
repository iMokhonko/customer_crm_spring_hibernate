package com.imokhonko.entities.validationRules;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Censorship validator which validates the field for inappropriate values.
 * @see CensorshipValidator current implementation.
 */
@Constraint(validatedBy = CensorshipValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Сensorship {

    /**
     * String array for forbidden values to be not contained in the field.
     * @return string array
     */
    String[] forbiddenSubstrings() default {};

    /**
     * Message that will be showed if filed contains forbidden substrings.
     * @return string message
     */
    String message() default "this is not allowed";

    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};

}
