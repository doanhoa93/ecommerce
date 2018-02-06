package com.framgia.service;

import java.util.List;

import com.framgia.bean.UserInfo;
import com.framgia.model.Cart;
import com.framgia.model.Comment;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Profile;
import com.framgia.model.User;

public interface UserService extends BaseService<Integer, User> {
	User findByEmail(String email);

	Profile getProfile(Integer userId);

	Cart getCart(Integer userId, Integer productId);

	List<Comment> getComments(Integer userId);

	List<OrderProduct> getOrderProducts(Integer userId);

	List<Product> getOrderedProducts(Integer userId);

	List<Product> getCartedProducts(Integer userId);

	boolean validate(UserInfo userInfo);
	
	void unremember(User user);
}
