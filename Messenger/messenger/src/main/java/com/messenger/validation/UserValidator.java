package com.messenger.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.messenger.entities.User;
import com.messenger.services.interfaces.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "Required");
		Pattern pattern = Pattern.compile("^\\+?3?8?(0[1-9][0-9]\\d{7})$");
		Matcher matcher = pattern.matcher(user.getPhone());
		if (!matcher.matches())
			errors.rejectValue("phone", "Size");

		if (userService.findUserByPhoneNumber(user.getPhone()) != null) {
			errors.rejectValue("phone", "Duplicate");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Size");
		}

		pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
		matcher = pattern.matcher(user.getPassword());
		if (!matcher.matches())
			errors.rejectValue("password", "Weak");

		/*
		 * if (!user.getConfirmPassword().equals(user.getPassword())) {
		 * errors.rejectValue("confirmPassword", "Different.userForm.password"); }
		 */

	}

}
