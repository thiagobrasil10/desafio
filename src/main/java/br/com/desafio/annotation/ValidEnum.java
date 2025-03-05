package br.com.desafio.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = EnumValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnum {

    String message() default "Campo 'Tipo Usuário' inválido. Opções possíveis: ADMIN, EDITOR, VIEWER";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // Especifica qual enum você quer validar
    Class<? extends Enum<?>> enumClass();


}
