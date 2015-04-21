package br.com.viniciusmrosa.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.ISBNValidator;

public class ValidadorISBN implements ConstraintValidator<ISBN, String> {

	@Override
	public void initialize(ISBN isbn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		ISBNValidator validador = ISBNValidator.getInstance();
		
		return validador.isValid(value);
	}

}
