package com.framgia.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.RequestContextHolder;

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

	public void validateCreate(Object target, UserInfo userInfo, Errors errors) {
		CartInfo cartInfo = (CartInfo) target;
		Integer productId = cartInfo.getProductId();
		ProductInfo productInfo = productService.findById(productId);
		if (productInfo == null) {
			errors.rejectValue("product", "cart.product.invalid");
			return;
		}

		if (userInfo != null) {
			CartInfo oldCart = cartService.getCart(userInfo.getId(), productId);

			cartInfo.setProductId(productId);
			cartInfo.setUserId(userInfo.getId());

			if (oldCart != null && cartInfo.getQuantity() + oldCart.getQuantity() > oldCart.getProduct().getNumber())
				errors.rejectValue("quantity", "cart.quantity.invalid");

			if (cartInfo.getQuantity() > productInfo.getNumber())
				errors.rejectValue("quantity", "cart.quantity.invalid");
		} else if (cartInfo.getQuantity() > productInfo.getNumber()) {
			errors.rejectValue("quantity", "cart.quantity.invalid");
		}

		cartInfo.setSessionId(currentSession());
	}

	public void validateUpdate(Object target, UserInfo currentUser, Errors errors) {
		CartInfo cartInfo = (CartInfo) target;

		if (currentUser != null && cartInfo.getUser().getId() != currentUser.getId())
			errors.rejectValue("user", "system.permission.denied");

		if (cartInfo.getQuantity() <= 0)
			errors.rejectValue("quantity", "cart.quantity.invalid");

		if (cartInfo.getSessionId() != null && !currentSession().equals(cartInfo.getSessionId()))
			errors.reject("sessionId", "cart.session_id.invalid");

	}

	public boolean validateDelete(Object target, UserInfo currentUser) {
		CartInfo cartInfo = (CartInfo) target;

		if (currentUser != null && cartInfo.getUser().getId() != currentUser.getId())
			return false;

		return currentSession().equals(cartInfo.getSessionId());
	}

	private String currentSession() {
		return RequestContextHolder.currentRequestAttributes().getSessionId();
	}
}
