package br.com.viniciusmrosa.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=ValidadorISBN.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ISBN {
	String message() default "CEP Inválido";
	Class<?>[] groups() default {} ;
	Class<? extends Payload>[] payload() default {} ;
}
