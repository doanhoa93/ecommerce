package com.framgia.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.framgia.bean.CartInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;
import com.framgia.service.CartService;
import com.framgia.service.ProductService;

@Component
public class CartValidator implements Validator {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@Override
	public boolean supports(Class<?> clazz) {
		return CartInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	}

	public void validateCreate(Object target, Integer userId, Integer productId, Errors errors) {
		CartInfo cartInfo = (CartInfo) target;
		CartInfo oldCart = cartService.getCart(userId, productId);

		cartInfo.setProductId(productId);
		cartInfo.setUserId(userId);

		if (oldCart != null && cartInfo.getQuantity() + oldCart.getQuantity() > oldCart.getProduct().getNumber())
			errors.rejectValue("quantity", "cart.quantity.invalid");
		else {
			ProductInfo productInfo = productService.findById(productId);
			if (productInfo != null && cartInfo.getQuantity() > productInfo.getNumber())
				errors.rejectValue("quantity", "cart.quantity.invalid");
		}
	}

	public void validateUpdate(Object target, UserInfo currentUser, Errors errors) {
		CartInfo cartInfo = (CartInfo) target;

		if (cartInfo.getUser().getId() != currentUser.getId())
			errors.rejectValue("user", "system.permission.denied");

		if (cartInfo.getQuantity() <= 0)
			errors.rejectValue("quantity", "cart.quantity.invalid");
	}

	public boolean validateDelete(Object target, UserInfo currentUser) {
		CartInfo cartInfo = (CartInfo) target;

		if (cartInfo.getUser().getId() != currentUser.getId())
			return false;

		return true;
	}
}
