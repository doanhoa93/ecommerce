package com.framgia.validator;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.framgia.bean.SuggestInfo;
import com.framgia.constant.Status;

@Component
public class SuggestValidator implements Validator {
	private static final List<String> IMAGE_TYPES = Arrays.asList("image/png", "image/jpeg", "image/gif");
	private static final long MAX_SIZE = 10485760;

	@Override
	public boolean supports(Class<?> clazz) {
		return SuggestInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SuggestInfo suggestInfo = (SuggestInfo) target;
		if (suggestInfo.getAvatarFile() == null)
			errors.rejectValue("avatarFile", "suggest.avatar.required");
		validateAvatar(suggestInfo.getAvatarFile(), errors);
		ValidationUtils.rejectIfEmpty(errors, "name", "suggest.name.empty");
		ValidationUtils.rejectIfEmpty(errors, "information", "suggest.information.empty");
		if (suggestInfo.getPrice() <= 0)
			errors.rejectValue("price", "suggest.price.invalid");
	}

	private void validateAvatar(MultipartFile avatar, Errors errors) {
		if (avatar != null) {
			if (avatar.isEmpty())
				errors.rejectValue("avatarFile", "suggest.avatar.required");

			if (!IMAGE_TYPES.contains(avatar.getContentType()))
				errors.rejectValue("avatarFile", "suggest.avatar.invalid_type");

			if (avatar.getSize() > MAX_SIZE)
				errors.rejectValue("avatarFile", "upload.exceeded.file.size");
		}
	}

	public void validStatus(String newStatus, Errors errors) {
		int status = Status.getIntStatus(newStatus);
		int begin = Status.getIntStatus(Status.WAITING);
		int end = Status.getIntStatus(Status.CANCEL);
		if (status < begin || status > end)
			errors.rejectValue("status", "suggest.status.inlavid");
	}

	public void validUpdate(Object oldObject, Object newObject, Errors errors) {
		SuggestInfo oldSuggest = (SuggestInfo) oldObject;
		SuggestInfo newSuggest = (SuggestInfo) newObject;

		if (StringUtils.isEmpty(newSuggest.getName()))
			errors.rejectValue("name", "suggest.name.empty");

		if (StringUtils.isEmpty(newSuggest.getInformation()))
			errors.rejectValue("information", "suggest.information.empty");

		if (newSuggest.getPrice() <= 0)
			errors.rejectValue("price", "suggest.price.invalid");

		if (oldSuggest.getUser().getId() != newSuggest.getUserId())
			errors.rejectValue("user", "suggest.user.invalid");

		MultipartFile avatar = newSuggest.getAvatarFile();
		if (avatar != null && !avatar.isEmpty()) {
			if (!IMAGE_TYPES.contains(avatar.getContentType()))
				errors.rejectValue("avatarFile", "suggest.avatar.invalid_type");

			if (avatar.getSize() > MAX_SIZE)
				errors.rejectValue("avatarFile", "upload.exceeded.file.size");
		}
	}
}
