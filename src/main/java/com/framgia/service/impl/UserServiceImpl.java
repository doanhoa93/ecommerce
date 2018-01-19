package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Cart;
import com.framgia.model.Comment;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Profile;
import com.framgia.model.User;
import com.framgia.service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService {
	@Override
	public User findByEmail(String email) {
		return getUserDAO().findByEmail(email);
	}

	@Override
	public Profile getProfile(Integer userId) {
		return getUserDAO().getProfile(userId);
	}

	@Override
	public List<Order> getOrders(Integer userId) {
		return getUserDAO().getOrders(userId);
	}

	@Override
	public List<Cart> getCarts(Integer userId) {
		return getUserDAO().getCarts(userId);
	}

	@Override
	public List<Comment> getComments(Integer userId) {
		return getUserDAO().getComments(userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderProduct> getOrderProducts(Integer userId) {
		List<Order> orders = getUserDAO().getOrders(userId);
		List<Integer> orderIds = (List<Integer>) orders.stream().map(Order::getId);
		return getOrderProductDAO().getList(orderIds);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getOrderedProducts(Integer userId) {
		return (List<Product>) getOrderProducts(userId).stream().map(OrderProduct::getProduct);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getCartedProducts(Integer userId) {
		return (List<Product>) getCarts(userId).stream().map(Cart::getProduct);
	}

	@Override
	public Cart getCart(Integer userId, Integer productId) {
		return getUserDAO().getCart(userId, productId);
	}

	@Override
	public User findBy(String attribute, Serializable key) {
		return getUserDAO().findBy(attribute, key);
	}

	@Override
	public User findById(Serializable key) {
		return getUserDAO().findById(key);
	}

	@Override
	public void delete(User entity) {
		try {
			getUserDAO().delete(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void saveOrUpdate(User entity) {
		try {
			getUserDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<User> getList() {
		return getUserDAO().getList();
	}

	@Override
	public List<User> getList(List<Integer> keys) {
		return getUserDAO().getList(keys);
	}

	@Override
	public List<User> getList(int limit) {
		return getUserDAO().getList(limit);
	}
}
