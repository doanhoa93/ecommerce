package com.framgia.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.framgia.bean.RateInfo;
import com.framgia.constant.Rating;
import com.framgia.service.RateService;

@Component
public class RateValidator implements Validator {

	@Autowired
	private RateService rateService;

	@Override
	public boolean supports(Class<?> clazz) {
		return RateInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RateInfo rateInfo = (RateInfo) target;
		if (rateInfo.getUser() == null && rateInfo.getUserId() == null)
			errors.rejectValue("user", "rate.user.empty");

		if (rateInfo.getProduct() == null && rateInfo.getProductId() == null)
			errors.rejectValue("product", "rate.product.empty");

		if (errors.getFieldError("user") == null && errors.getFieldError("product") == null
		    && rateService.getRate(rateInfo.getUserId(), rateInfo.getProductId()) != null)
			errors.rejectValue("id", "rate.exist");

		if (rateInfo.getRating() < Rating.MIN || rateInfo.getRating() > Rating.MAX)
			errors.rejectValue("rating", "rate.rating.invalid");
	}

	public void validateUpdate(RateInfo oldRate, RateInfo newRate, Errors errors) {
		if (oldRate.getUser().getId() != newRate.getUserId())
			errors.rejectValue("user", "rate.user.invalid");

		if (oldRate.getProduct().getId() != newRate.getProductId())
			errors.rejectValue("product", "rate.product.invalid");

		if (newRate.getRating() < Rating.MIN || newRate.getRating() > Rating.MAX)
			errors.rejectValue("rating", "rate.rating.invalid");
	}
}
