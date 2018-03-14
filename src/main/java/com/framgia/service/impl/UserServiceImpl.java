package com.framgia.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.WebUtils;

import com.framgia.bean.CommentInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.ProfileInfo;
import com.framgia.bean.UserInfo;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Order;
import com.framgia.model.User;
import com.framgia.service.UserService;
import com.framgia.util.Encode;

public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Override
	public ProfileInfo getProfile(Integer userId) {
		try {
			return ModelToBean.toProfileInfo(getUserDAO().getProfile(userId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<CommentInfo> getComments(Integer userId) {
		try {
			return getUserDAO().getComments(userId).stream().map(ModelToBean::toCommentInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<OrderProductInfo> getOrderProducts(Integer userId) {
		try {
			List<Order> orders = getOrderDAO().getOrders(userId, 0, 0, null);
			List<Integer> orderIds = (List<Integer>) orders.stream().map(Order::getId).collect(Collectors.toList());
			return getOrderProductDAO().getObjectsByIds(orderIds).stream().map(ModelToBean::toOrderProductInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ProductInfo> getOrderedProducts(Integer userId) {
		try {
			return getOrderProducts(userId).stream().map(OrderProductInfo::getProduct).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ProductInfo> getCartedProducts(Integer userId) {
		try {
			return getCartDAO().getCarts(userId, 0, 0, null).stream().map(cart -> {
				return ModelToBean.toProductInfo(cart.getProduct());
			}).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public UserInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toUserInfo(getUserDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public UserInfo findByEmail(String email) {
		try {
			return findBy("email", email, true);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public UserInfo findById(Serializable key) {
		try {
			return findBy("id", key, true);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean delete(UserInfo entity) {
		try {
			getUserDAO().delete(toUser(entity));
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public UserInfo saveOrUpdate(UserInfo entity) {
		try {
			return ModelToBean.toUserInfo(getUserDAO().saveOrUpdate(toUser(entity)));
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<UserInfo> getObjects() {
		try {
			return getUserDAO().getObjects().stream().map(ModelToBean::toUserInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<UserInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getUserDAO().getObjectsByIds(keys).stream().map(ModelToBean::toUserInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<UserInfo> getObjects(int off, int limit) {
		try {
			return getUserDAO().getObjects(off, limit).stream().map(ModelToBean::toUserInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean validate(UserInfo userInfo) {
		try {
			UserInfo userInf = getFromCookie(request);
			if (userInf == null) {
				userInf = findByEmail(userInfo.getEmail());
				if (userInf != null && userInf.getPassword().equals(Encode.encode(userInfo.getPassword()))) {
					if (userInfo.isRemember()) {
						Cookie cookie = new Cookie("email", userInfo.getEmail());
						cookie.setPath("/");
						cookie.setMaxAge(2592000);
						response.addCookie(cookie);
					}

					request.getSession().setAttribute("currentUser", userInf);
					return true;
				}
			} else {
				request.getSession().setAttribute("currentUser", userInf);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public UserInfo getFromCookie(HttpServletRequest request) {
		try {
			Cookie cookie = WebUtils.getCookie(request, "email");
			if (cookie == null)
				return null;
			return findByEmail(cookie.getValue());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public void unremember() {
		try {
			Cookie cookie = new Cookie("email", "");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		} catch (Exception e) {
			logger.error(e);
			return;
		}
	}

	@Override
	public List<UserInfo> getUsers(String role) {
		try {
			return getUserDAO().getUsers(role).stream().map(ModelToBean::toUserInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<UserInfo> getNewUsers(Date date, int limit) {
		try {
			return getUserDAO().getNewObjects(date, limit).stream().map(ModelToBean::toUserInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<UserInfo> getUsers(int off, int limit, org.hibernate.criterion.Order order) {
		try {
			return getUserDAO().getUsers(off, limit, order).stream().map(ModelToBean::toUserInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private User toUser(UserInfo userInfo) {
		User user = getUserDAO().getFromSession(userInfo.getId());
		if (user == null) {
			user = new User();
			user.setId(userInfo.getId());
		}

		user.setName(userInfo.getName());
		user.setEmail(userInfo.getEmail());
		user.setPassword(Encode.encode(userInfo.getPassword()));
		user.setRole(userInfo.getRole());
		user.setAvatar(userInfo.getAvatar());
		user.setCreatedAt(new Date());
		return user;
	}
}
