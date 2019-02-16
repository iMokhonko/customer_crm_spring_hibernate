package com.imokhonko.entities.validationRules;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CensorshipValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Сensorship {

    String[] forbiddenSubstrings() default {};
    String message() default "this is not allowed";

    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};

}
