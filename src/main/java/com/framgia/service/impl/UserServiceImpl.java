package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.framgia.bean.UserInfo;
import com.framgia.model.Cart;
import com.framgia.model.Comment;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Profile;
import com.framgia.model.User;
import com.framgia.service.UserService;
import com.framgia.util.Encode;

public class UserServiceImpl extends BaseServiceImpl implements UserService {

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
		return getOrderProductDAO().getObjectsByIds(orderIds);
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
	public User findBy(String attribute, Serializable key, boolean lock) {
		return getUserDAO().findBy(attribute, key, lock);
	}

	@Override
	public User findByEmail(String email) {
		return findBy("email", email, true);
	}

	@Override
	public User findById(Serializable key) {
		return findBy("id", key, true);
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
	public List<User> getObjects() {
		return getUserDAO().getObjects();
	}

	@Override
	public List<User> getObjectsByIds(List<Integer> keys) {
		return getUserDAO().getObjectsByIds(keys);
	}

	@Override
	public List<User> getObjects(int limit) {
		return getUserDAO().getObjects(limit);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean validate(UserInfo userInfo) {
		User user = checkCookie(request);
		if (user == null) {
			user = findByEmail(userInfo.getEmail());
			if (user != null && user.getPassword().equals(Encode.encode(userInfo.getPassword()))) {
				if (userInfo.isRemember()) {
					Cookie cookie = new Cookie("email", userInfo.getEmail());
					cookie.setMaxAge(1);
					cookie.setPath("/");
					response.addCookie(cookie);
				}

				request.getSession().putValue("currentUser", user);
				return true;
			}
		} else {
			request.getSession().putValue("currentUser", user);
			return true;
		}
		return false;
	}

	private User checkCookie(HttpServletRequest request) {
		User user = null;
		for (Cookie cookie : request.getCookies()) {
			user = findByEmail(cookie.getValue());
			if (user != null)
				break;
		}
		return user;
	}

	@Override
	public void unremember(User user) {
		Cookie cookie = new Cookie("email", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		cookie.setValue(null);
		response.addCookie(cookie);
	}
}
