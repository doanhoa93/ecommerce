package com.framgia.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.framgia.bean.ProductInfo;

@Component
public class ProductValidator implements Validator {
	private static final List<String> IMAGE_TYPES = Arrays.asList("image/png", "image/jpeg", "image/gif");
	private static final long MAX_SIZE = 10485760;

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "product.name.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryId", "product.category.null");

		ProductInfo productInfo = (ProductInfo) target;
		MultipartFile avatar = productInfo.getAvatarFile();
		if (!avatar.isEmpty()) {
			if (!IMAGE_TYPES.contains(avatar.getContentType()))
				errors.rejectValue("avatarFile", "image.invalid_type", IMAGE_TYPES.toArray(), Locale.US.toString());
			if (avatar.getSize() > MAX_SIZE)
				errors.rejectValue("avatarFile", "image.invalid_size", new Object[] { MAX_SIZE / 1048576 }, Locale.US.toString());
		}

		if (productInfo.getPrice() < 0)
			errors.rejectValue("price", "product.price.invalid");

		if (productInfo.getNumber() < 0)
			errors.rejectValue("number", "product.number.invalid");

		if (productInfo.getIsPromotion())
			if (productInfo.getSaleOff() <= 0 || productInfo.getSaleOff() >= 100)
				errors.rejectValue("saleOff", "product.sale_off.invalid");

		if (productInfo.getImageFiles() != null)
			for (MultipartFile image : productInfo.getImageFiles()) {
				if (!image.isEmpty()) {
					if (!IMAGE_TYPES.contains(image.getContentType()))
						errors.rejectValue("imageFiles", "image.invalid_type", IMAGE_TYPES.toArray(),
						        Locale.US.toString());
					if (image.getSize() > MAX_SIZE)
						errors.rejectValue("imageFiles", "image.invalid_size", new Object[] { MAX_SIZE },
						        Locale.US.toString());
				}
			}
		else
			productInfo.setImageFiles(new ArrayList<>());

		if (productInfo.getImageIds() == null)
			productInfo.setImageIds(new ArrayList<>());

		if (productInfo.getImagesStatus() == null)
			productInfo.setImagesStatus(new ArrayList<>());
	}
}
