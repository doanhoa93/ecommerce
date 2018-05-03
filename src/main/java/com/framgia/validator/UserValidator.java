package com.framgia.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
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
		UserInfo userInfo = (UserInfo) target;
		if (StringUtils.isEmpty(userInfo.getPassword()))
			errors.rejectValue("password", "user.password.empty");

		if (StringUtils.isEmpty(userInfo.getEmail()))
			errors.rejectValue("password", "user.email.empty");

		if (StringUtils.isEmpty(userInfo.getName()))
			errors.rejectValue("password", "user.name.empty");
	}

	public void validateUpdate(UserInfo userInfo, Errors errors) {
		UserInfo oldUser = userService.findById(userInfo.getId(), false);
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		if (StringUtils.isEmpty(userInfo.getPassword())
		    || !bcrypt.matches(userInfo.getPassword(), oldUser.getPassword()))
			errors.rejectValue("password", "user.password.invalid");

		if (StringUtils.isEmpty(userInfo.getName()))
			errors.rejectValue("name", "user.name.empty");

		if (StringUtils.isEmpty(userInfo.getProfile().getPhoneNumber()))
			errors.rejectValue("profile.phoneNumber", "user.phone_number.empty");
	}
}
