package com.framgia.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.framgia.bean.CartInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;
import com.framgia.helper.CustomSession;
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

	public void validateCreate(Object target, Errors errors) {
		CartInfo cartInfo = (CartInfo) target;
		Integer productId = cartInfo.getProductId();
		ProductInfo productInfo = productService.findById(productId, false);
		UserInfo userInfo = cartInfo.getUser();
		if (productInfo == null) {
			errors.rejectValue("product", "cart.product.invalid");
			return;
		}

		if (userInfo != null) {
			CartInfo oldCart = cartService.getCart(userInfo.getId(), productId);

			cartInfo.setProductId(productId);
			cartInfo.setUserId(userInfo.getId());

			if (oldCart != null && cartInfo.getQuantity() + oldCart.getQuantity() > oldCart
			    .getProduct().getNumber())
				errors.rejectValue("quantity", "cart.quantity.invalid");

			if (cartInfo.getQuantity() > productInfo.getNumber())
				errors.rejectValue("quantity", "cart.quantity.invalid");
		} else if (cartInfo.getQuantity() > productInfo.getNumber()) {
			errors.rejectValue("quantity", "cart.quantity.invalid");
		}

		cartInfo.setSessionId(CustomSession.current());
	}

	public void validateUpdate(Object target, UserInfo currentUser, Errors errors) {
		CartInfo cartInfo = (CartInfo) target;

		if (currentUser != null && cartInfo.getUser().getId() != currentUser.getId())
			errors.rejectValue("user", "system.permission.denied");

		if (cartInfo.getQuantity() <= 0)
			errors.rejectValue("quantity", "cart.quantity.invalid");

		if (cartInfo.getSessionId() != null
		    && !CustomSession.current().equals(cartInfo.getSessionId()))
			errors.reject("sessionId", "cart.session_id.invalid");

	}

	public boolean validateDelete(Object target, UserInfo currentUser) {
		CartInfo cartInfo = (CartInfo) target;

		if (currentUser != null && cartInfo.getUser().getId() == currentUser.getId())
			return true;

		return CustomSession.current().equals(cartInfo.getSessionId());
	}
}
