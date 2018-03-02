package com.framgia.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Order;

import com.framgia.bean.CommentInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.ProfileInfo;
import com.framgia.bean.UserInfo;

public interface UserService extends BaseService<Integer, UserInfo> {
	UserInfo findByEmail(String email);

	UserInfo findByToken(String token);
	
	ProfileInfo getProfile(Integer userId);

	List<CommentInfo> getComments(Integer userId);

	List<OrderProductInfo> getOrderProducts(Integer userId);

	List<ProductInfo> getOrderedProducts(Integer userId);

	List<ProductInfo> getCartedProducts(Integer userId);

	List<UserInfo> getUsers(String role);

	List<UserInfo> getNewUsers(Date date, int limit);

	List<UserInfo> getUsers(int off, int limit, Order order);

	boolean validate(UserInfo user);

	UserInfo getFromCookie(HttpServletRequest request);

	void unremember();
}
