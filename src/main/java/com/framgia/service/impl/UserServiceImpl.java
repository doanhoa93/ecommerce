package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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
		try {
			return getUserDAO().getProfile(userId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Order> getOrders(Integer userId) {
		try {
			return getUserDAO().getOrders(userId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Cart> getCarts(Integer userId) {
		try {
			return getUserDAO().getCarts(userId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Comment> getComments(Integer userId) {
		try {
			return getUserDAO().getComments(userId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<OrderProduct> getOrderProducts(Integer userId) {
		try {
			List<Order> orders = getUserDAO().getOrders(userId);
			List<Integer> orderIds = (List<Integer>) orders.stream().map(Order::getId).collect(Collectors.toList());
			return getOrderProductDAO().getObjectsByIds(orderIds);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> getOrderedProducts(Integer userId) {
		try {
			return (List<Product>) getOrderProducts(userId).stream().map(OrderProduct::getProduct)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> getCartedProducts(Integer userId) {
		try {
			return (List<Product>) getCarts(userId).stream().map(Cart::getProduct).collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Cart getCart(Integer userId, Integer productId) {
		try {
			return getUserDAO().getCart(userId, productId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User findBy(String attribute, Serializable key, boolean lock) {
		try {
			return getUserDAO().findBy(attribute, key, lock);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		try {
			return findBy("email", email, true);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User findById(Serializable key) {
		try {
			return findBy("id", key, true);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(User entity) {
		try {
			getUserDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(User entity) {
		try {
			getUserDAO().saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<User> getObjects() {
		try {
			return getUserDAO().getObjects();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User> getObjectsByIds(List<Integer> keys) {
		try {
			return getUserDAO().getObjectsByIds(keys);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User> getObjects(int limit) {
		try {
			return getUserDAO().getObjects(limit);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean validate(UserInfo userInfo) {
		try {
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
		} catch (Exception e) {
			return false;
		}
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
		try {
			Cookie cookie = new Cookie("email", "");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			cookie.setValue(null);
			response.addCookie(cookie);
		} catch (Exception e) {
			return;
		}
	}
}
