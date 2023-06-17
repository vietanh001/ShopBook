package com.example.cuoiky.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.cuoiky.model.User;
import com.example.cuoiky.service.UserService;


@Component
public class UserValidator implements Validator{
	
	private final UserService userService;

	public UserValidator(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmptyUN");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmptyPW");
        if(userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "DuplicateUN");
		}
        if(user.getPassword().length() < 8 || user.getPassword().length() > 32) {
        	errors.rejectValue("password", "SizePassword");
        }
	}
}
