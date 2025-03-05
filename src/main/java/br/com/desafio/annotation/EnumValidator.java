package br.com.desafio.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {

    private Enum<?>[] enumConstants;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.enumConstants = constraintAnnotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Se o campo for nulo, a validação será tratada por outras anotações, como @NotNull
        }

        // Verifica se o valor passado é um valor válido para o enum
        return Arrays.asList(enumConstants).contains(value);
    }
}