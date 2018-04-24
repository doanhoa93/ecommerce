package com.framgia.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.framgia.bean.PromotionInfo;
import com.framgia.constant.SaleOf;

@Component
public class PromotionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PromotionInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PromotionInfo promotionInfo = (PromotionInfo) target;
		if (promotionInfo.getSaleOf() > SaleOf.max || promotionInfo.getSaleOf() <= SaleOf.min)
			errors.rejectValue("saleOf", "promotion.sale_of.invalid");
	}

	public void validateUpdate(PromotionInfo oldPromotion, PromotionInfo newPromotion,
	    Errors errors) {
		if (newPromotion.getSaleOf() > SaleOf.max || newPromotion.getSaleOf() <= SaleOf.min)
			errors.rejectValue("saleOf", "promotion.sale_of.invalid");
	}
}
