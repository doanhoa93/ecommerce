package com.framgia.helper;

import java.util.Date;

import com.framgia.bean.CartInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.SuggestInfo;
import com.framgia.bean.UserInfo;
import com.framgia.model.Cart;
import com.framgia.model.Category;
import com.framgia.model.Product;
import com.framgia.model.Suggest;
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

	public static Product toProduct(ProductInfo productInfo) {
		Product product = new Product();
		product.setId(productInfo.getId());
		product.setAvatar(productInfo.getAvatar());
		product.setCategory(new Category(productInfo.getCategoryId()));
		product.setInformation(productInfo.getInformation());
		product.setIsPromotion(productInfo.getIsPromotion());
		product.setName(productInfo.getName());
		product.setNumber(productInfo.getNumber());
		product.setPrice(productInfo.getPrice());
		product.setPromotionId(productInfo.getPromotionId());
		product.setRating(productInfo.getRating());
		product.setSaleOf(productInfo.getSaleOf());
		return product;
	}

	public static Suggest toSuggest(SuggestInfo suggestInfo) {
		Suggest suggest = new Suggest();
		if (suggestInfo.getAvatarFile() != null)
			suggest.setAvatar(suggestInfo.getAvatarFile().getOriginalFilename());
		suggest.setCategory(suggestInfo.getCategory());
		suggest.setName(suggestInfo.getName());
		suggest.setCreatedAt(new Date());
		suggest.setPrice(suggestInfo.getPrice());
		suggest.setUser(new User(suggestInfo.getUserId()));
		suggest.setInformation(suggestInfo.getInformation());
		return suggest;
	}
}
