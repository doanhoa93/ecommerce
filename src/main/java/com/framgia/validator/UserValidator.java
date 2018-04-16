package com.framgia.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import com.framgia.bean.UserInfo;
import com.framgia.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	}

	public void validateUpdate(UserInfo userInfo, Errors errors) {
		UserInfo oldUser = userService.findById(userInfo.getId());
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		if (!bcrypt.matches(userInfo.getPassword(), oldUser.getPassword()))
			errors.rejectValue("password", "user.password.invalid");

		if (StringUtils.isEmpty(userInfo.getName()))
			errors.rejectValue("name", "user.name.empty");

		if (StringUtils.isEmpty(userInfo.getProfile().getPhoneNumber()))
			errors.rejectValue("profile.phoneNumber", "user.phone_number.empty");

		removeErrorBirthDay(errors);
	}

	private void removeErrorBirthDay(Errors errors) {
		for (FieldError fieldError : errors.getFieldErrors()) {
			if (fieldError.getField().equals("profile.birthd ay")) {
				errors.getFieldErrors().remove(fieldError);
				break;
			}
		}

	}
}
