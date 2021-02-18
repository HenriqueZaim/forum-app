package com.br.ng.forum.common.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmailMustBeUniqueValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailMustBeUnique {
    
    String message() default "Este endereço de e-mail já está em uso";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
