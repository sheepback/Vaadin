package com.example.testme.client.validator;

import com.vaadin.data.validator.AbstractValidator;

/**
 * @author Alexander Thomas
 * @date 22:10:41 07.01.2016
 */
@SuppressWarnings("serial")
public final class PasswordValidator extends AbstractValidator<String> {

	public PasswordValidator() {
		super("The password provided is not valid");
	}

	@Override
	protected boolean isValidValue(String value) {
		//
		// Password must be at least 5 characters long and contain at least
		// one number
		//
		if (value != null && (value.length() < 5 || !value.matches(".*\\d.*"))) {
			return false;
		}
		return true;
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}
}