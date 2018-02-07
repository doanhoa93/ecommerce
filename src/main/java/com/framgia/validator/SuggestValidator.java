package com.framgia.validator;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.framgia.bean.SuggestInfo;

@Component
public class SuggestValidator implements Validator {
	private static final List<String> IMAGE_TYPES = Arrays.asList("image/png", "image/jpeg", "image/gif");
	public static final long MAX_SIZE = 1048576;

	@Override
	public boolean supports(Class<?> clazz) {
		return SuggestInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SuggestInfo suggestInfo = (SuggestInfo) target;
		MultipartFile avatar = suggestInfo.getAvatarFile();
		if (avatar.isEmpty()) {
			errors.rejectValue("avatar", "suggest.avatar.required");
		} else if (!IMAGE_TYPES.contains(avatar.getContentType())) {
			errors.rejectValue("avatar", "suggest.avatar.invalid_type");
		} else if (avatar.getSize() > MAX_SIZE) {
			errors.rejectValue("avatar", "upload.exceeded.file.size");
		}
	}
}
