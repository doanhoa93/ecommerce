package com.framgia.helper;

import com.framgia.bean.CartInfo;
import com.framgia.bean.UserInfo;
import com.framgia.model.Cart;
import com.framgia.model.Product;
import com.framgia.model.User;
import com.framgia.util.Encode;

public class BeanToModel {
	public static User toUser(UserInfo userInfo) {
		User user = new User();
		user.setId(userInfo.getId());
		user.setName(userInfo.getName());
		user.setEmail(userInfo.getEmail());
		user.setPassword(Encode.encode(userInfo.getPassword()));
		user.setRole("User");
		user.setAvatar(userInfo.getAvatar());
		return user;
	}

	public static Cart toCart(CartInfo cartInfo) {
		Cart cart = new Cart();
		Product product = new Product();
		product.setId(cartInfo.getProductId());
		cart.setProduct(product);
		User user = new User();
		user.setId(cartInfo.getUserId());
		cart.setUser(user);
		cart.setQuantity(cartInfo.getQuantity());
		return cart;
	}
}
