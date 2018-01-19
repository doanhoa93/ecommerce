package com.framgia.dao;

import java.util.List;

import com.framgia.model.Cart;
import com.framgia.model.Comment;
import com.framgia.model.Order;
import com.framgia.model.Profile;
import com.framgia.model.User;

public interface UserDAO {
	User findById(Integer id);

	User findByEmail(String email);

	Profile getProfile(Integer userId);

	List<Order> getOrders(Integer userId);

	List<Cart> getCarts(Integer userId);

	List<Comment> getComments(Integer userId);

	List<User> getUsers(List<Integer> userIds);
}
