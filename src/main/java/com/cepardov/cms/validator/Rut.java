package com.cepardov.cms.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RutValidator.class)
@Documented
public @interface Rut {
    String message() default "inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
