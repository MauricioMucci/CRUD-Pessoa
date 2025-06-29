package com.senai.prova.infrastructure.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author MauricioMucci
 */
@NotBlank(message = "O nome é obrigatório.")
@Pattern(
        regexp = "^([A-Z][a-z]+)(\\s[A-Z][a-z]+)+$",
        message = "O nome deve ter mais de um nome e cada nome deve começar com maiúscula e as demais minúsculas."
)
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
public @interface NomeConstraint {

    String message() default "Nome de usuário inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
